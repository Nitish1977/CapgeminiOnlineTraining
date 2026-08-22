package com.onlinepharmacy.user_service.dto.profile;


import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String name;
    private String email;
    private String phone;
}
