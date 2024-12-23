package com.pradip.collection_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends AuditableDTO<String> {
    private Long id;
    private String name;
    private String email;
    private Long mobile;
    private String password;
}