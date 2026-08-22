package com.onlinepharmacy.user_service.dto;

import com.onlinepharmacy.user_service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse
{

    private Long id;
    private String name;
    private  String email;
    private  String phone;
    private Role role;
}
