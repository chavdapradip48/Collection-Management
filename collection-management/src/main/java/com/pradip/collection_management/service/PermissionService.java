package com.pradip.collection_management.service;

import com.pradip.collection_management.dto.PermissionDTO;
import com.pradip.collection_management.model.Permission;

import java.util.List;

public interface PermissionService {
    PermissionDTO createPermission(PermissionDTO permissionDTO);
    PermissionDTO updatePermission(PermissionDTO permissionDTO) throws Exception;
    PermissionDTO getPermission(long permissionId);
    PermissionDTO deletePermission(long permissionId);
    List<PermissionDTO> getPermissions();
}
