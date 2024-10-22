package com.pradip.collection_management.service;

import com.pradip.collection_management.dto.UserDTO;
import com.pradip.collection_management.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);
    User updateUser(UserDTO userDTO) throws Exception;
    User getUser(long userId);
    User deleteUser(long userId);
    List<User> getUsers();
}
