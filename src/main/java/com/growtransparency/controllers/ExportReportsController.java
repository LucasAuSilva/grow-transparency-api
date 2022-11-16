package com.growtransparency.controllers;

import com.growtransparency.helpers.CsvFileGenerator;
import com.growtransparency.repositories.ProjectRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/export")
@SecurityRequirement(name = "bearerAuth")
public class ExportReportsController {

    private final CsvFileGenerator csvFileGenerator;
    private final ProjectRepository projectRepository;

    public ExportReportsController(CsvFileGenerator csvFileGenerator, ProjectRepository projectRepository) {
        this.csvFileGenerator = csvFileGenerator;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CSV file returned"),
            @ApiResponse(responseCode = "403", description = "User not authenticated", content = @Content),
    })
    public void exportCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=projects" + new Date().getTime() + ".csv");
        var projects = projectRepository.findAll();
        csvFileGenerator.writeProjectsToCsv(projects, response.getWriter());
    }
}
