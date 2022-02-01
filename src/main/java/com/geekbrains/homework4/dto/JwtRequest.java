package com.geekbrains.homework4.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
