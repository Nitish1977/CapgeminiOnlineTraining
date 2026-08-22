package com.onlinepharmacy.admin_service.service;

import com.onlinepharmacy.admin_service.dto.UserResponse;

import java.util.List;

public interface AdminService {

    List<UserResponse> getAllUsers();
}
