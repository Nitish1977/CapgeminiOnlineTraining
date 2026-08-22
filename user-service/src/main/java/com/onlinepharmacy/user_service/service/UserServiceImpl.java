package com.onlinepharmacy.user_service.service;


import com.onlinepharmacy.user_service.dto.LoginRequest;
import com.onlinepharmacy.user_service.dto.SignupRequest;
import com.onlinepharmacy.user_service.dto.UserResponse;
import com.onlinepharmacy.user_service.dto.profile.UpdateProfileRequest;
import com.onlinepharmacy.user_service.entity.User;
import com.onlinepharmacy.user_service.enums.Role;
import com.onlinepharmacy.user_service.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse signup(SignupRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("User already exists with email: "+request.getEmail());
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .build();

        User savedUser = userRepository.save(user);
        return  mapToResponse(savedUser);
    }

    public UserResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("User is not found"));

        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }


    public UserResponse getProfile(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: "+userId));


        return mapToResponse(user);
    }

    @Override
    public UserResponse updateProfile( Long userId,  UpdateProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found with userId: "+ userId));

        if(!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists "+request.getEmail());
        }


        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone((request.getPhone()));
        User updateUser = userRepository.save(user);

        return mapToResponse(updateUser);

    }


    @Override
    public List<UserResponse> getAllUsers(){

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}
