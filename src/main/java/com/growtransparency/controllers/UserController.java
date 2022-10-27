package com.growtransparency.controllers;

import com.growtransparency.dtos.*;
import com.growtransparency.models.User;
import com.growtransparency.models.Roles;
import com.growtransparency.repositories.RoleRepository;
import com.growtransparency.repositories.UserRepository;
import com.growtransparency.services.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  public UserController(UserRepository userRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/register")
  public ResponseEntity<ReturnCreatedUserDTO> registerUser(@Valid @RequestBody CreateUserDTO dto) {
    User user = userRepository.save(dto.toEntity());
    return ResponseEntity.ok(new ReturnCreatedUserDTO(user));
  }

  @PostMapping("/login")
  public ResponseEntity<ReturnLoginUserDto> loginUser(@RequestBody LoginUserDTO dto) {
    var optional = userRepository.findByEmail(dto.getEmail());

    if (optional.isPresent()) {
      var loginData = dto.convert();
      var authentication = authenticationManager.authenticate(loginData);
      var token = tokenService.generateToken(authentication);

      return ResponseEntity.ok(new ReturnLoginUserDto(token, true));
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReturnLoginUserDto("", false));
  }

  // TODO atribuição de cargo admin
}
