package com.growtransparency.controllers;

import com.growtransparency.dtos.CreateUserDTO;
import com.growtransparency.dtos.ReturnCreatedUserDTO;
import com.growtransparency.models.User;
import com.growtransparency.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping("/register")
  public ReturnCreatedUserDTO registerUser(@RequestBody CreateUserDTO dto) {
    User user = userRepository.save(dto.toEntity());
    return new ReturnCreatedUserDTO(user);
  }
}
