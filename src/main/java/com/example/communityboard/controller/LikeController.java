package com.example.communityboard.controller;

import com.example.communityboard.entity.Post;
import com.example.communityboard.entity.User;
import com.example.communityboard.security.CustomUserDetails;
import com.example.communityboard.service.LikeService;
import com.example.communityboard.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LikeController {

    private final LikeService likeService;
    private final PostService postService;

    public LikeController(LikeService likeService, PostService postService) {
        this.likeService = likeService;
        this.postService = postService;
    }

    // いいねトグル処理
    @PostMapping("/posts/{id}/likes")
    public String toggle(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Post post = postService.findById(id);
        User loginUser = userDetails.getUser();
        likeService.toggle(post, loginUser);
        return "redirect:/posts/" + id;
    }
}