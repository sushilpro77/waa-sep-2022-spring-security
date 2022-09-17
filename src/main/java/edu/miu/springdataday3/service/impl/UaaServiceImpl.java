package edu.miu.springdataday3.service.impl;

import edu.miu.springdataday3.entity.auth.LoginRequest;
import edu.miu.springdataday3.entity.auth.LoginResponse;
import edu.miu.springdataday3.entity.auth.RefreshTokenRequest;
import edu.miu.springdataday3.security.JwtHelper;
import edu.miu.springdataday3.service.UaaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UaaServiceImpl implements UaaService {
    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword() ));

        } catch (BadCredentialsException e){
            //log.info("Bad Credentials");
            System.out.println("Bad Credentials");
        }

        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshTokenRequest.getRefreshToken());
        if( isRefreshTokenValid) {
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject( refreshTokenRequest.getRefreshToken() ));
            return new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
        }
        return new LoginResponse();
    }
}
