package com.pradip.collection_management.service;

import com.pradip.collection_management.dto.UserDTO;
import com.pradip.collection_management.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO) throws Exception;
    UserDTO getUser(long userId);
    UserDTO deleteUser(long userId);
    List<UserDTO> getUsers();
}
