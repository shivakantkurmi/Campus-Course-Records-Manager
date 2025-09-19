package edu.ccrm.io;

import edu.ccrm.domain.Student;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportExportService {
    private final StudentService studentService = new StudentService();

    public void importStudents(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.skip(1).forEach(line -> {
                String[] parts = line.split(",");
                Student student = new Student(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], true, null, LocalDate.parse(parts[4]));
                studentService.addStudent(student);
            });
        }
    }

    public void exportStudents(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = studentService.getStudents().stream()
                .map(s -> s.getId() + "," + s.getRegNo() + "," + s.getFullName() + "," + s.getEmail() + "," + s.getDateOfBirth())
                .collect(Collectors.toList());
        lines.add(0, "ID,RegNo,FullName,Email,DOB");
        Files.write(path, lines);
    }
}