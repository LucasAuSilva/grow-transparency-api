package com.growtransparency.controllers;

import com.growtransparency.dtos.CreateUserDTO;
import com.growtransparency.dtos.LoginUserDTO;
import com.growtransparency.dtos.ReturnCreatedUserDTO;
import com.growtransparency.dtos.ReturnLoginUserDto;
import com.growtransparency.models.User;
import com.growtransparency.repositories.RoleRepository;
import com.growtransparency.repositories.UserRepository;
import com.growtransparency.services.TokenService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@CrossOrigin
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
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "User created"),
    @ApiResponse(responseCode = "400", description = "Invalid request")})
  public ResponseEntity<ReturnCreatedUserDTO> registerUser(@Valid @RequestBody CreateUserDTO dto, UriComponentsBuilder uriBuilder) {
    User user = userRepository.save(dto.toEntity());
    URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).body(new ReturnCreatedUserDTO(user));
  }

  @PostMapping("/login")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
    @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
  })
  public ResponseEntity<ReturnLoginUserDto> loginUser(@Valid @RequestBody LoginUserDTO dto) {
    var optional = userRepository.findByEmail(dto.email());

    if (optional.isPresent()) {
      var loginData = dto.convert();
      var authentication = authenticationManager.authenticate(loginData);
      var token = tokenService.generateToken(authentication);

      var authorities = optional.get().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
      return ResponseEntity.ok(new ReturnLoginUserDto(token, true, authorities));
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @SecurityRequirement(name = "authBearer")
  @PutMapping("/admin/{id}")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "User updated", content = @Content),
    @ApiResponse(responseCode = "418", description = "User already admin", content = @Content),
    @ApiResponse(responseCode = "403", description = "User authenticated but not admin", content = @Content),
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
  })
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
