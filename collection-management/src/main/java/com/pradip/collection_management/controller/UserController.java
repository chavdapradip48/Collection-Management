package com.pradip.collection_management.controller;

import com.pradip.collection_management.dto.UserDTO;
import com.pradip.collection_management.model.User;
import com.pradip.collection_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestBody UserDTO userDTO, @PathVariable Long userId) throws Exception {
        userDTO.setId(userId);
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{userId}")
    public User deleteUser(long userId) {
        return userService.deleteUser(userId);
    }
}
