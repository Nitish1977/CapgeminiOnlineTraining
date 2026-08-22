package com.onlinepharmacy.user_service.service;


import com.onlinepharmacy.user_service.dto.LoginRequest;
import com.onlinepharmacy.user_service.dto.LoginResponse;
import com.onlinepharmacy.user_service.dto.UserResponse;
import com.onlinepharmacy.user_service.entity.User;
import com.onlinepharmacy.user_service.repository.UserRepository;
import com.onlinepharmacy.user_service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final AuthenticationManager authenticationManager;
    private  final JwtService jwtService;
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}
