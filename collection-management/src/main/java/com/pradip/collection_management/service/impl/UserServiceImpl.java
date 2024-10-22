package com.pradip.collection_management.service.impl;


import com.pradip.collection_management.dto.UserDTO;
import com.pradip.collection_management.model.User;
import com.pradip.collection_management.repository.UserRepository;
import com.pradip.collection_management.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User createUser(UserDTO userDTO) {

        User userToSave = modelMapper.map(userDTO, User.class);
        return userRepository.save(userToSave);
    }

    @Override
    public User updateUser(UserDTO userDTO) throws Exception {

        if(userDTO.getId() == null || userDTO.getId() == 0) throw new BadRequestException("Please enter the user Id.");

        User userFromDB = userRepository.findById(userDTO.getId()).orElseThrow(() -> new EntityNotFoundException("The user with ID 1 does not exist."));
        userFromDB.setName(userDTO.getName());
        userFromDB.setEmail(userDTO.getEmail());
        userFromDB.setMobile(userDTO.getMobile());
        userFromDB.setPassword(userDTO.getPassword());

        return userRepository.save(userFromDB);
    }

    @Override
    public User getUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("The user with ID 1 does not exist."));
    }

    @Override
    public User deleteUser(long userId) {
        User userFromDB = getUser(userId);
        userRepository.delete(userFromDB);
        return userFromDB;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
