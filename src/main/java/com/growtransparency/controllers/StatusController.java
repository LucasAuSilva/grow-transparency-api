package com.growtransparency.controllers;


import com.growtransparency.dtos.ReturnListStatusDTO;
import com.growtransparency.repositories.StatusRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/status")
@SecurityRequirement(name = "bearerAuth")
public class StatusController {

    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public ResponseEntity<List<ReturnListStatusDTO>> getAllStatus() {
        var status = statusRepository.findAll();
        return ResponseEntity.ok(ReturnListStatusDTO.convert(status));
    }
}
