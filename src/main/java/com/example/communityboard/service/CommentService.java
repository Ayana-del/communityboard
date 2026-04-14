package com.example.communityboard.service;

import com.example.communityboard.dto.CommentForm;
import com.example.communityboard.entity.Comment;
import com.example.communityboard.entity.Post;
import com.example.communityboard.entity.User;
import com.example.communityboard.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // 投稿に紐づくコメント一覧取得
    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }

    // コメント投稿
    @Transactional
    public void create(CommentForm form, Post post, User loginUser) {
        Comment comment = new Comment();
        comment.setBody(form.getBody());
        comment.setPost(post);
        comment.setUser(loginUser);
        commentRepository.save(comment);
    }
}