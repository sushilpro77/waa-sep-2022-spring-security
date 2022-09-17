package edu.miu.springdataday3.service;

import edu.miu.springdataday3.entity.auth.LoginRequest;
import edu.miu.springdataday3.entity.auth.LoginResponse;
import edu.miu.springdataday3.entity.auth.RefreshTokenRequest;

public interface UaaService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
