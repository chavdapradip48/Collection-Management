package com.pradip.collection_management.controller;

import com.pradip.collection_management.dto.ApiResponse;
import com.pradip.collection_management.dto.PermissionDTO;
import com.pradip.collection_management.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pradip.collection_management.MessageConstants.*;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getPermissions() {
        return ResponseEntity.ok(new ApiResponse<Object>(HttpStatus.OK,PERMISSIONS+CREATED_MESSAGE,permissionService.getPermissions()));
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<ApiResponse<PermissionDTO>> getPermission(@PathVariable long permissionId) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,PERMISSION+RETRIVED_MESSAGE,permissionService.getPermission(permissionId)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionDTO>> createPermission(@RequestBody PermissionDTO permissionDTO) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED,PERMISSION+CREATED_MESSAGE,permissionService.createPermission(permissionDTO)));
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<ApiResponse<PermissionDTO>> updatePermission(@RequestBody PermissionDTO permissionDTO, @PathVariable Long permissionId) throws Exception {
        permissionDTO.setId(permissionId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,PERMISSION+UPDATED_MESSAGE,permissionService.updatePermission(permissionDTO)));
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<ApiResponse<PermissionDTO>> deletePermission(long permissionId) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,PERMISSION+DELETED_MESSAGE,permissionService.deletePermission(permissionId)));
    }
}