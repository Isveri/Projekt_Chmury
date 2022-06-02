package com.example.project.controllers;

import com.example.project.model.PostListDTO;
import com.example.project.model.UserDTO;
import com.example.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.save(userDTO));
    }


    @GetMapping
    public ResponseEntity<UserDTO> getAlreadyLoggedUser(){
        return ResponseEntity.ok(userService.getLoggedUser());
    }

    @GetMapping("/my-posts")
    public ResponseEntity<PostListDTO> getUserPosts(){
        return ResponseEntity.ok(userService.getUserPosts());
    }


    @PutMapping("/edit")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUserByDTO(userDTO));
    }

}
