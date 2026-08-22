package com.onlinepharmacy.admin_service.service;

import com.onlinepharmacy.admin_service.client.UserClient;
import com.onlinepharmacy.admin_service.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserClient userClient;
    @Override
    public List<UserResponse> getAllUsers() {
        return userClient.getAllUsers();
    }
}
