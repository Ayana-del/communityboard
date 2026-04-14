package com.example.communityboard.service;

import com.example.communityboard.dto.PostForm;
import com.example.communityboard.entity.Category;
import com.example.communityboard.entity.Post;
import com.example.communityboard.entity.User;
import com.example.communityboard.repository.PostRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 全件取得（新着順）
    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // カテゴリ絞り込み
    public List<Post> findByCategory(Category category) {
        return postRepository.findByCategoryOrderByCreatedAtDesc(category);
    }

    // ID指定で1件取得
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("投稿が見つかりません: " + id));
    }

    // 投稿作成
    @Transactional
    public void create(PostForm form, User loginUser) {
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setBody(form.getBody());
        post.setCategory(form.getCategory());
        post.setUser(loginUser);
        postRepository.save(post);
    }

    // 投稿更新
    @Transactional
    public void update(Long id, PostForm form, User loginUser) {
        Post post = findById(id);
        checkOwner(post, loginUser);
        post.setTitle(form.getTitle());
        post.setBody(form.getBody());
        post.setCategory(form.getCategory());
        postRepository.save(post);
    }

    // 投稿削除
    @Transactional
    public void delete(Long id, User loginUser) {
        Post post = findById(id);
        checkOwner(post, loginUser);
        postRepository.delete(post);
    }

    // 投稿者チェック（本人以外は403エラー）
    public void checkOwner(Post post, User loginUser) {
        if (!post.getUser().getId().equals(loginUser.getId())) {
            throw new AccessDeniedException("この操作は許可されていません");
        }
    }
}