package com.onlinepharmacy.user_service.service;

import com.onlinepharmacy.user_service.dto.LoginRequest;
import com.onlinepharmacy.user_service.dto.SignupRequest;
import com.onlinepharmacy.user_service.dto.UserResponse;
import com.onlinepharmacy.user_service.dto.profile.UpdateProfileRequest;

import java.util.List;

public interface UserService {
  UserResponse signup(SignupRequest request);
  UserResponse login(LoginRequest request);

  UserResponse getProfile(Long userId);

  UserResponse updateProfile(Long userId, UpdateProfileRequest request);

  List<UserResponse> getAllUsers();
}
