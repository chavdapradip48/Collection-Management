package com.pradip.collection_management.service;

import com.pradip.collection_management.dto.RoleDTO;
import com.pradip.collection_management.model.Role;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(RoleDTO roleDTO) throws Exception;
    RoleDTO getRole(long roleId);
    RoleDTO deleteRole(long roleId);
    List<RoleDTO> getRoles();
}