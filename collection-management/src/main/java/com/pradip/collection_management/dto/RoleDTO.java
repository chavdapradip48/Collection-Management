package com.pradip.collection_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends AuditableDTO<String>{
    private Long id;
    private String name;
    private String description;
    private Set<PermissionDTO> permissions;
}
