package com.example.communityboard.controller;

import com.example.communityboard.dto.CommentForm;
import com.example.communityboard.entity.Post;
import com.example.communityboard.entity.User;
import com.example.communityboard.security.CustomUserDetails;
import com.example.communityboard.service.CommentService;
import com.example.communityboard.service.PostService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    // コメント投稿処理
    @PostMapping("/posts/{id}/comments")
    public String create(
            @PathVariable Long id,
            @Valid @ModelAttribute CommentForm form,
            BindingResult bindingResult,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentError", "コメントを入力してください");
            return "redirect:/posts/" + id;
        }

        Post post = postService.findById(id);
        User loginUser = userDetails.getUser();
        commentService.create(form, post, loginUser);
        return "redirect:/posts/" + id;
    }
}