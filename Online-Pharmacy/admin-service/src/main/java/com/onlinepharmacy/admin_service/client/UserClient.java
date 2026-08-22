package com.onlinepharmacy.admin_service.client;


import com.onlinepharmacy.admin_service.config.FeignConfig;
import com.onlinepharmacy.admin_service.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-service",
configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/api/users")
    List<UserResponse> getAllUsers();
}
