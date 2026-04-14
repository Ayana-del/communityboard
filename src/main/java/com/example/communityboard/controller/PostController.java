package com.example.communityboard.controller;

import com.example.communityboard.dto.PostForm;
import com.example.communityboard.entity.Category;
import com.example.communityboard.entity.Post;
import com.example.communityboard.entity.User;
import com.example.communityboard.security.CustomUserDetails;
import com.example.communityboard.service.LikeService;
import com.example.communityboard.service.PostService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final LikeService likeService;

    public PostController(PostService postService, LikeService likeService) {
        this.postService = postService;
        this.likeService = likeService;
    }

    // 投稿一覧表示
    @GetMapping("/posts")
    public String index(
            @RequestParam(required = false) String category,
            Model model) {

        List<Post> posts;
        if (category != null && !category.isEmpty()) {
            posts = postService.findByCategory(Category.valueOf(category));
            model.addAttribute("selectedCategory", category);
        } else {
            posts = postService.findAll();
        }

        model.addAttribute("posts", posts);
        model.addAttribute("categories", Category.values());
        return "posts/index";
    }

    // 投稿詳細表示
    @GetMapping("/posts/{id}")
    public String show(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {

        Post post = postService.findById(id);
        long likeCount = likeService.countByPost(id);
        boolean liked = false;

        if (userDetails != null) {
            liked = likeService.isLiked(id, userDetails.getUser().getId());
        }

        model.addAttribute("post", post);
        model.addAttribute("likeCount", likeCount);
        model.addAttribute("liked", liked);
        return "posts/show";
    }

    // 投稿作成フォーム表示
    @GetMapping("/posts/new")
    public String newForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("categories", Category.values());
        return "posts/new";
    }

    // 投稿作成処理
    @PostMapping("/posts")
    public String create(
            @Valid @ModelAttribute PostForm form,
            BindingResult bindingResult,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", Category.values());
            return "posts/new";
        }

        User loginUser = userDetails.getUser();
        postService.create(form, loginUser);
        return "redirect:/posts";
    }

    // 投稿編集フォーム表示
    @GetMapping("/posts/{id}/edit")
    public String editForm(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {

        Post post = postService.findById(id);
        postService.checkOwner(post, userDetails.getUser());

        PostForm form = new PostForm();
        form.setTitle(post.getTitle());
        form.setBody(post.getBody());
        form.setCategory(post.getCategory());

        model.addAttribute("postForm", form);
        model.addAttribute("postId", id);
        model.addAttribute("categories", Category.values());
        return "posts/edit";
    }

    // 投稿更新処理
    @PostMapping("/posts/{id}/update")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute PostForm form,
            BindingResult bindingResult,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("postId", id);
            model.addAttribute("categories", Category.values());
            return "posts/edit";
        }

        postService.update(id, form, userDetails.getUser());
        return "redirect:/posts/" + id;
    }

    // 投稿削除処理
    @PostMapping("/posts/{id}/delete")
    public String delete(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        postService.delete(id, userDetails.getUser());
        return "redirect:/posts";
    }
}