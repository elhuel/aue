package org.example.coworking_rent.dto;

import lombok.Data;

@Data
public class AuthUserResponse {
    private Long userId;
    private String role;
    private String email;
}