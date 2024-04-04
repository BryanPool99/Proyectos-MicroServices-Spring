package com.bryan.UserRegistration_Service.services.impl;

import com.bryan.UserRegistration_Service.model.dto.UserRequest;
import com.bryan.UserRegistration_Service.model.dto.UserResponse;
import com.bryan.UserRegistration_Service.model.entities.User;
import com.bryan.UserRegistration_Service.repositories.UserRepository;
import com.bryan.UserRegistration_Service.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserResponse> getAllUser() {
        var users=userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }
    /*
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    */
    @Override
    public User saveUser(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }
       var newUser=User.builder()
               .name(userRequest.getName())
               .lastname(userRequest.getLastname())
               .email(userRequest.getEmail())
               .password(userRequest.getPassword())
               .build();
        log.info("User added: {}", newUser);
       return userRepository.save(newUser);
    }
    /*
    @Override
    public User updateUser(Long id, UserRequest userRequest) {
        return null;
    }
    */
    /*
    @Override
    public void deleteUser(Long id) {

    }
    */
}
