package com.example.project.services;


import com.example.project.model.UserDTO;
import com.example.project.model.auth.TokenResponse;
import com.example.project.model.auth.UserCredentials;

public interface AuthService {

    TokenResponse getToken(UserCredentials userCredentials);

    TokenResponse createNewAccount(UserDTO userDto);
}
