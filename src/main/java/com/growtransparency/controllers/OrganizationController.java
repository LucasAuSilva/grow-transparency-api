package com.growtransparency.controllers;

import com.growtransparency.dtos.CreateOrganizationDTO;
import com.growtransparency.dtos.ReturnCreatedOrganizationDTO;
import com.growtransparency.dtos.ReturnOrganizationsDTO;
import com.growtransparency.models.Organization;
import com.growtransparency.repositories.OrganizationRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/organization")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "*")
public class OrganizationController {
  private final OrganizationRepository organizationRepository;

  public OrganizationController(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
  }

  @PostMapping
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Organization created"),
    @ApiResponse(responseCode = "403", description = "User not authenticated", content = @Content),
  })
  public ResponseEntity<ReturnCreatedOrganizationDTO> createOrganization(@RequestBody CreateOrganizationDTO dto) {
    Organization organization = organizationRepository.save(dto.toEntity());
    return ResponseEntity.ok(new ReturnCreatedOrganizationDTO(organization));
  }

  @GetMapping
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Organizations returned"),
    @ApiResponse(responseCode = "204", description = "Doesn't have any Organizations", content = @Content),
    @ApiResponse(responseCode = "403", description = "User not authenticated", content = @Content),
  })
  public ResponseEntity<List<ReturnOrganizationsDTO>> showOrganizations() {
    List<Organization> organizations = organizationRepository.findAll();

    if (organizations.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ReturnOrganizationsDTO.convert(new ArrayList<>()));
    }

    return ResponseEntity.status(HttpStatus.OK).body(ReturnOrganizationsDTO.convert(organizations));
  }
}
