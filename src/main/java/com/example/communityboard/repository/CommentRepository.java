package com.example.communityboard.repository;

import com.example.communityboard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 投稿に紐づくコメントを作成順で取得
    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);
}