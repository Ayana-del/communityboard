package com.example.communityboard.repository;

import com.example.communityboard.entity.Category;
import com.example.communityboard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 全件を新着順で取得
    List<Post> findAllByOrderByCreatedAtDesc();

    // カテゴリ絞り込み（新着順）
    List<Post> findByCategoryOrderByCreatedAtDesc(Category category);

    // 投稿者IDで取得（新着順）
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);
}