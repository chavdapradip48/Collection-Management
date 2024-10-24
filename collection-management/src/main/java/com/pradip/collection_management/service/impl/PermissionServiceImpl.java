package com.pradip.collection_management.service.impl;

import com.pradip.collection_management.dto.PermissionDTO;
import com.pradip.collection_management.dto.UserDTO;
import com.pradip.collection_management.model.Permission;
import com.pradip.collection_management.model.User;
import com.pradip.collection_management.repository.PermissionRepository;
import com.pradip.collection_management.service.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PermissionDTO createPermission(PermissionDTO PermissionDTO) {
        Permission PermissionToSave = modelMapper.map(PermissionDTO, Permission.class);
        return permissionEntityToDTO(permissionRepository.save(PermissionToSave));
    }

    @Override
    public PermissionDTO updatePermission(PermissionDTO permissionDTO) throws Exception {

        if(permissionDTO.getId() == null || permissionDTO.getId() == 0) throw new BadRequestException("Please enter the Permission Id.");

        Permission permissionFromDB = permissionRepository.findById(permissionDTO.getId()).orElseThrow(() -> new EntityNotFoundException("The Permission with ID "+permissionDTO.getId()+" does not exist."));
        permissionFromDB.setName(permissionDTO.getName());
        permissionFromDB.setDescription(permissionDTO.getDescription());
        permissionFromDB.setResource(permissionDTO.getResource());
        permissionFromDB.setAction(permissionDTO.getAction());

        return permissionEntityToDTO(permissionRepository.save(permissionFromDB));
    }

    @Override
    public PermissionDTO getPermission(long permissionId) {
        return permissionEntityToDTO(permissionRepository.findById(permissionId).orElseThrow(() -> new EntityNotFoundException("The Permission with ID "+permissionId+" does not exist.")));
    }

    @Override
    public PermissionDTO deletePermission(long permissionId) {
        Permission permissionFromDB = permissionRepository.findById(permissionId).orElseThrow(() -> new EntityNotFoundException("The Permission with ID "+permissionId+" does not exist."));
        permissionRepository.delete(permissionFromDB);
        return permissionEntityToDTO(permissionFromDB);
    }

    @Override
    public List<PermissionDTO> getPermissions() {
        return permissionRepository.findAll().stream().map(this::permissionEntityToDTO).toList();
    }

    private PermissionDTO permissionEntityToDTO(Permission permission) {
        return modelMapper.map(permission, PermissionDTO.class);
    }
}
