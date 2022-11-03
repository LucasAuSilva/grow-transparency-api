package com.growtransparency.controllers;

import com.growtransparency.dtos.CreateUserDTO;
import com.growtransparency.dtos.LoginUserDTO;
import com.growtransparency.dtos.ReturnCreatedUserDTO;
import com.growtransparency.dtos.ReturnLoginUserDto;
import com.growtransparency.models.User;
import com.growtransparency.repositories.RoleRepository;
import com.growtransparency.repositories.UserRepository;
import com.growtransparency.services.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin
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

  @PutMapping("/admin/{id}")
  public ResponseEntity<?> nominateAdmin(@PathVariable Long id) {
    var optional = userRepository.findById(id);
    var rolesOptional = roleRepository.findById(1L);

    if (optional.isPresent()) {
      if (optional.get().getAuthorities().contains(rolesOptional.get())) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
      }

      optional.get().addRole(rolesOptional.get());
      userRepository.save(optional.get());

      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
