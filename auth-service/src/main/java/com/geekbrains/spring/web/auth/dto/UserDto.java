package com.geekbrains.spring.web.auth.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
}
