package com.example.project.services;

import com.example.project.domain.Role;
import com.example.project.domain.User;
import com.example.project.exceptions.NotFoundException;
import com.example.project.mappers.UserMapper;
import com.example.project.mappers.UserPostListMapper;
import com.example.project.model.PostListDTO;
import com.example.project.model.UserDTO;
import com.example.project.repositories.PostRepository;
import com.example.project.repositories.RoleRepository;
import com.example.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserPostListMapper userPostListMapper;
    private final RoleRepository roleRepository;


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user->userMapper.mapUserToUserDTO(user))
                .collect(Collectors.toList());
    }


    @Override
    public UserDTO save(UserDTO userDTO) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        User user = userMapper.mapUserDTOToUser(userDTO);
        user.setRole(userRole);
        return userMapper.mapUserToUserDTO(userRepository.save(user));
    }


    @Override
    public UserDTO saveAndReturnDTO(User user) {
        return userMapper.mapUserToUserDTO(userRepository.save(user));
    }


    @Override
    public UserDTO updateUserByDTO(UserDTO userDTO) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = currentUser.getId();
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found id:"+id));
        user.setId(id);
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return saveAndReturnDTO(user);
    }

    @Override
    public UserDTO getLoggedUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = currentUser.getId();
        return userRepository.findById(id)
                .map(userMapper::mapUserToUserDTO)
                .map(userDTO -> {
                    userDTO.setId(id);
                    return userDTO;})
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public PostListDTO getUserPosts() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = currentUser.getId();
        return userRepository.findById(id)
                .map(userPostListMapper::mapUserToPostListDTO).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
