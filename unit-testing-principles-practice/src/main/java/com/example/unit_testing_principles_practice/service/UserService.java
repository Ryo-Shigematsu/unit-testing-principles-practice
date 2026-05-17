package com.example.unit_testing_principles_practice.service;

import com.example.unit_testing_principles_practice.dto.UserRegistrationRequest;
import com.example.unit_testing_principles_practice.dto.UserRegistrationResponse;

public interface UserService {
    
    /**
     * ユーザーを登録します
     * @param request ユーザー登録リクエスト
     * @return ユーザー登録レスポンス
     * @throws IllegalArgumentException 入力値が不正な場合
     * @throws IllegalStateException メールアドレスが既に登録されている場合
     */
    UserRegistrationResponse registerUser(UserRegistrationRequest request);
    
    /**
     * メールアドレスが既に登録されているかを確認します
     * @param email メールアドレス
     * @return 登録済みの場合true、未登録の場合false
     */
    boolean isEmailAlreadyRegistered(String email);
}
