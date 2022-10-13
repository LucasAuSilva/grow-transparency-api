package com.growtransparency.controllers;

import com.growtransparency.dtos.CreateUserDTO;
import com.growtransparency.dtos.LoginUserDTO;
import com.growtransparency.dtos.ReturnCreatedUserDTO;
import com.growtransparency.dtos.ReturnLoginUserDto;
import com.growtransparency.models.User;
import com.growtransparency.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping("/register")
  public ResponseEntity<ReturnCreatedUserDTO> registerUser(@Valid @RequestBody CreateUserDTO dto) {
    User user = userRepository.save(dto.toEntity());
    return ResponseEntity.ok(new ReturnCreatedUserDTO(user));
  }

  // TODO: configurar token de autenticação de senha
  @PostMapping("/login")
  public ResponseEntity<ReturnLoginUserDto> loginUser(@RequestBody LoginUserDTO dto) {
    var optional = userRepository.findByEmail(dto.getEmail());

    if (optional.isPresent()) {
      if (optional.get().getPassword().equals(dto.getPassword())) {
        return ResponseEntity.ok(new ReturnLoginUserDto(true));
      }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReturnLoginUserDto(false));
  }
}
