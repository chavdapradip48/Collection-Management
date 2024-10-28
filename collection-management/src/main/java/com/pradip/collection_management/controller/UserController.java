package com.pradip.collection_management.controller;

import com.pradip.collection_management.dto.ApiResponseDTO;
import com.pradip.collection_management.dto.UserDTO;
import com.pradip.collection_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pradip.collection_management.constant.MessageConstants.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "User management APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get users", description = "Fetch all the users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = USERS + RETRIVED_MESSAGE),
            @ApiResponse(responseCode = "404", description = USERS + NOT_FOUND_MESSAGE_MULTI)
    })
    @GetMapping
    public ResponseEntity<ApiResponseDTO<Object>> getUsers() {
        return ResponseEntity.ok(new ApiResponseDTO<Object>(HttpStatus.OK, USERS+RETRIVED_MESSAGE, userService.getUsers()));
    }

    @Operation(summary = "Get user By Id", description = "Fetch the user by user id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = USER + RETRIVED_MESSAGE),
            @ApiResponse(responseCode = "404", description = USER + NOT_FOUND_MESSAGE)
    })
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<UserDTO>> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK, USER+RETRIVED_MESSAGE, userService.getUser(userId)));
    }

    @Operation(summary = "Update user", description = "Update the user in our system by passing valid body and also pass valid user id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = USER + UPDATED_MESSAGE),
            @ApiResponse(responseCode = "500", description = USER + NOT_UPDATED_MESSAGE)
    })
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<UserDTO>> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long userId) throws Exception {
        userDTO.setId(userId);
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK, USER+UPDATED_MESSAGE, userService.updateUser(userDTO)));
    }

    @Operation(summary = "Create User", description = "Create the user by passing required body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = USER + CREATED_MESSAGE),
            @ApiResponse(responseCode = "500", description = USER + NOT_CREATED_MESSAGE)
    })
    @PostMapping
    public ResponseEntity<ApiResponseDTO<UserDTO>> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.CREATED, USER+CREATED_MESSAGE, userService.createUser(userDTO)));
    }

    @Operation(summary = "Delete User", description = "Delete the user by valid user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = USER + DELETED_MESSAGE),
            @ApiResponse(responseCode = "404", description = USER + NOT_DELETED_MESSAGE)
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<UserDTO>> deleteUser(long userId) {
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK, USER+DELETED_MESSAGE, userService.deleteUser(userId)));
    }
}
