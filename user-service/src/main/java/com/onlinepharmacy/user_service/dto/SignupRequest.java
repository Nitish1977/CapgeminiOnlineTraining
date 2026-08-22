package com.onlinepharmacy.user_service.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignupRequest {
    private String name;
    private String email;
    private String phone;
    private String password;

}
