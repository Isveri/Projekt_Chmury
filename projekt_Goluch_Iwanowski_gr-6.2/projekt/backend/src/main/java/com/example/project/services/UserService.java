package com.example.project.services;

import com.example.project.domain.User;
import com.example.project.model.UserDTO;
import com.example.project.model.PostListDTO;

import java.util.List;


public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO save(UserDTO user);
    UserDTO saveAndReturnDTO(User user);
    UserDTO updateUserByDTO(UserDTO userDTO);
    UserDTO getLoggedUser();
    PostListDTO getUserPosts();

}
