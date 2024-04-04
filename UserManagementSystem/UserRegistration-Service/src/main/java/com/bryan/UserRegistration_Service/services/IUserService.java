package com.bryan.UserRegistration_Service.services;

import com.bryan.UserRegistration_Service.model.dto.UserRequest;
import com.bryan.UserRegistration_Service.model.dto.UserResponse;
import com.bryan.UserRegistration_Service.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserResponse> getAllUser();
    //Optional<User> getUserById(Long id);
    User saveUser(UserRequest userRequest);
    //User updateUser(Long id, UserRequest userRequest);
    //void deleteUser(Long id);
}
