package com.example.unit_testing_principles_practice.service;

import com.example.unit_testing_principles_practice.domain.entity.User;
import com.example.unit_testing_principles_practice.dto.UserRegistrationRequest;
import com.example.unit_testing_principles_practice.dto.UserRegistrationResponse;
import com.example.unit_testing_principles_practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_USERNAME_LENGTH = 3;
    
    @Override
    @Transactional
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
        // 入力値の検証
        validateRegistrationRequest(request);
        
        // メールアドレスの重複チェック
        if (isEmailAlreadyRegistered(request.getEmail())) {
            throw new IllegalStateException("このメールアドレスは既に登録されています");
        }
        
        // ユーザーを作成して保存
        User user = new User(
            request.getEmail(),
            request.getPassword(),
            request.getUsername()
        );
        
        User savedUser = userRepository.save(user);
        
        // レスポンスを作成して返却
        return new UserRegistrationResponse(
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getUsername(),
            savedUser.getEnabled(),
            "ユーザー登録が完了しました"
        );
    }
    
    @Override
    public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }
    
    /**
     * ユーザー登録リクエストの入力値を検証します
     * @param request ユーザー登録リクエスト
     * @throws IllegalArgumentException 入力値が不正な場合
     */
    private void validateRegistrationRequest(UserRegistrationRequest request) {
        // メールアドレスの検証
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("メールアドレスを入力してください");
        }
        
        if (!isValidEmail(request.getEmail())) {
            throw new IllegalArgumentException("有効なメールアドレスを入力してください");
        }
        
        // パスワードの検証
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("パスワードを入力してください");
        }
        
        if (request.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("パスワードは8文字以上である必要があります");
        }
        
        // パスワード確認の検証
        if (request.getPasswordConfirm() == null || request.getPasswordConfirm().isEmpty()) {
            throw new IllegalArgumentException("パスワード確認を入力してください");
        }
        
        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new IllegalArgumentException("パスワードとパスワード確認が一致しません");
        }
        
        // ユーザー名の検証
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("ユーザー名を入力してください");
        }
        
        if (request.getUsername().length() < MIN_USERNAME_LENGTH) {
            throw new IllegalArgumentException("ユーザー名は3文字以上である必要があります");
        }
    }
    
    /**
     * メールアドレスが有効な形式であるかを確認します
     * @param email メールアドレス
     * @return 有効な場合true、無効な場合false
     */
    private boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }
}
