package com.example.communityboard.service;

import com.example.communityboard.entity.Like;
import com.example.communityboard.entity.Post;
import com.example.communityboard.entity.User;
import com.example.communityboard.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // いいねトグル（済みなら削除・未済なら登録）
    @Transactional
    public void toggle(Post post, User loginUser) {
        if (likeRepository.existsByPostIdAndUserId(post.getId(), loginUser.getId())) {
            // いいね取り消し
            likeRepository.findByPostIdAndUserId(post.getId(), loginUser.getId())
                    .ifPresent(likeRepository::delete);
        } else {
            // いいね登録
            Like like = new Like();
            like.setPost(post);
            like.setUser(loginUser);
            likeRepository.save(like);
        }
    }

    // いいね済みかチェック
    public boolean isLiked(Long postId, Long userId) {
        return likeRepository.existsByPostIdAndUserId(postId, userId);
    }

    // いいね数取得
    public long countByPost(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}