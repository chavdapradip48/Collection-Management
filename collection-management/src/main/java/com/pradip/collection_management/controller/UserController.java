package com.pradip.collection_management.controller;

import com.pradip.collection_management.dto.ApiResponse;
import com.pradip.collection_management.dto.UserDTO;
import com.pradip.collection_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pradip.collection_management.constant.MessageConstants.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getUsers() {
        return ResponseEntity.ok(new ApiResponse<Object>(HttpStatus.OK,USERS+RETRIVED_MESSAGE,userService.getUsers()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,USER+RETRIVED_MESSAGE,userService.getUser(userId)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED,USER+CREATED_MESSAGE,userService.createUser(userDTO)));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long userId) throws Exception {
        userDTO.setId(userId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,USER+UPDATED_MESSAGE,userService.updateUser(userDTO)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> deleteUser(long userId) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,USER+DELETED_MESSAGE,userService.deleteUser(userId)));
    }
}
