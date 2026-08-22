package com.onlinepharmacy.admin_service.dto;

import com.onlinepharmacy.admin_service.dto.enums.Role;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private  String email;
    private String phone;
    private Role role;

}
