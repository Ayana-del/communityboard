package com.example.communityboard.service;

import com.example.communityboard.dto.RegisterForm;
import com.example.communityboard.entity.User;
import com.example.communityboard.repository.UserRepository;
import com.example.communityboard.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Spring Securityのログイン認証で自動呼び出し
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + email));
        return new CustomUserDetails(user);
    }

    // ユーザー登録
    @Transactional
    public void register(RegisterForm form) {
        // メールアドレス重複チェック
        if (userRepository.existsByEmail(form.getEmail())) {
            throw new IllegalArgumentException("このメールアドレスはすでに登録されています");
        }

        // パスワード一致チェック
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            throw new IllegalArgumentException("パスワードが一致しません");
        }

        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        userRepository.save(user);
    }
}