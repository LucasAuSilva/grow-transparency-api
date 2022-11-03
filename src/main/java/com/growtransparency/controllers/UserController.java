package com.growtransparency.controllers;

import com.growtransparency.dtos.CreateUserDTO;
import com.growtransparency.dtos.LoginUserDTO;
import com.growtransparency.dtos.ReturnCreatedUserDTO;
import com.growtransparency.dtos.ReturnLoginUserDto;
import com.growtransparency.models.User;
import com.growtransparency.repositories.RoleRepository;
import com.growtransparency.repositories.UserRepository;
import com.growtransparency.services.TokenService;
import com.growtransparency.settings.errors.ResponseErrorValidation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
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
  public ResponseEntity<ReturnLoginUserDto> loginUser(@RequestBody LoginUserDTO dto) {
    var optional = userRepository.findByEmail(dto.getEmail());

    if (optional.isPresent()) {
      var loginData = dto.convert();
      var authentication = authenticationManager.authenticate(loginData);
      var token = tokenService.generateToken(authentication);

      return ResponseEntity.ok(new ReturnLoginUserDto(token, true));
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @PutMapping("/admin/{id}")
  @SecurityRequirements(value = {
    @SecurityRequirement(name = "authBearer")
  })
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
