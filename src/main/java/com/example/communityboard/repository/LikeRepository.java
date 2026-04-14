package com.example.communityboard.repository;

import com.example.communityboard.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // いいね済みかチェック
    boolean existsByPostIdAndUserId(Long postId, Long userId);

    // いいね取り消し用
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    // いいね数カウント
    long countByPostId(Long postId);
}