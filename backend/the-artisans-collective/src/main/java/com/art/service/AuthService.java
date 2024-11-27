package com.art.service;

import com.art.domain.USER_ROLE;
import com.art.request.LoginRequest;
import com.art.response.AuthResponse;
import com.art.response.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse signing(LoginRequest req) throws Exception;
}
