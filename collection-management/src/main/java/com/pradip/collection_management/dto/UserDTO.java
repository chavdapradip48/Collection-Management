package com.pradip.collection_management.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Long mobile;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
