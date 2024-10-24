package com.pradip.collection_management.service.impl;

import com.pradip.collection_management.dto.RoleDTO;
import com.pradip.collection_management.dto.UserDTO;
import com.pradip.collection_management.model.Permission;
import com.pradip.collection_management.model.Role;
import com.pradip.collection_management.model.User;
import com.pradip.collection_management.repository.RoleRepository;
import com.pradip.collection_management.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role roleToSave = modelMapper.map(roleDTO, Role.class);
        return roleEntityToDTO(roleRepository.save(roleToSave));
    }

    @Override
    public RoleDTO updateRole(RoleDTO roleDTO) throws Exception {

        if(roleDTO.getId() == null || roleDTO.getId() == 0) throw new BadRequestException("Please enter the Role Id.");

        Role roleFromDB = roleRepository.findById(roleDTO.getId()).orElseThrow(() -> new EntityNotFoundException("The Role with ID "+roleDTO.getId()+" does not exist."));
        roleFromDB.setName(roleDTO.getName());
        roleFromDB.setDescription(roleDTO.getDescription());

        Set<Permission> permissions = roleDTO.getPermissions().stream()
                .map(permissionDTO -> modelMapper.map(permissionDTO, Permission.class))
                .collect(Collectors.toSet());

        roleFromDB.setPermissions(permissions);

        return roleEntityToDTO(roleRepository.save(roleFromDB));
    }

    @Override
    public RoleDTO getRole(long roleId) {
        return roleEntityToDTO(roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("The Role with ID "+roleId+" does not exist.")));
    }

    @Override
    public RoleDTO deleteRole(long roleId) {
        Role roleFromDB = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("The Role with ID "+roleId+" does not exist."));
        roleRepository.delete(roleFromDB);
        return roleEntityToDTO(roleFromDB);
    }

    @Override
    public List<RoleDTO> getRoles() {
        return roleRepository.findAll().stream().map(this::roleEntityToDTO).toList();
    }

    private RoleDTO roleEntityToDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }
}
