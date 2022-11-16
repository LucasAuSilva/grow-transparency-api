package com.growtransparency.helpers;

import com.growtransparency.models.Project;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvFileGenerator {
    public void writeProjectsToCsv(List<Project> projects, Writer writer) throws IOException {
        var printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        projects.forEach(project -> {
            try {
                printer.printRecord(
                        project.getId(),
                        project.getName(),
                        project.getCost(),
                        project.getDescription(),
                        project.getScore(),
                        project.getTotalTime(),
                        project.getStatus(),
                        project.getLink());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
