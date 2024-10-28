package com.pradip.collection_management.controller;

import com.pradip.collection_management.dto.ApiResponseDTO;
import com.pradip.collection_management.dto.RoleDTO;
import com.pradip.collection_management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pradip.collection_management.constant.MessageConstants.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Object>> getRoles() {
        return ResponseEntity.ok(new ApiResponseDTO<Object>(HttpStatus.OK,ROLES+RETRIVED_MESSAGE,roleService.getRoles()));
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> getRole(@PathVariable long roleId) {
        return ResponseEntity.ok(new ApiResponseDTO<RoleDTO>(HttpStatus.OK,ROLE+RETRIVED_MESSAGE,roleService.getRole(roleId)));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<RoleDTO>> createRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(new ApiResponseDTO<RoleDTO>(HttpStatus.CREATED,ROLE+CREATED_MESSAGE,roleService.createRole(roleDTO)));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> updateRole(@RequestBody RoleDTO roleDTO, @PathVariable Long roleId) throws Exception {
        roleDTO.setId(roleId);
        return ResponseEntity.ok(new ApiResponseDTO<RoleDTO>(HttpStatus.CREATED,ROLE+UPDATED_MESSAGE,roleService.updateRole(roleDTO)));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> deleteRole(long roleId) {
        return ResponseEntity.ok(new ApiResponseDTO<RoleDTO>(HttpStatus.CREATED,ROLE+DELETED_MESSAGE,roleService.deleteRole(roleId)));
    }
}
