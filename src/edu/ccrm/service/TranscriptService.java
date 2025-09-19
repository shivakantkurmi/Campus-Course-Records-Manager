package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;

import java.util.StringJoiner;

public class TranscriptService {
    public String generateTranscript(Student student) {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("Transcript for " + student.getFullName());
        student.getEnrolledCourses().forEach(e -> sj.add(e.toString()));
        sj.add("GPA: " + calculateGPA(student));
        return sj.toString();
    }

    public double calculateGPA(Student student) {
        java.util.List<Enrollment> enrollments = student.getEnrolledCourses();
        if (enrollments.isEmpty()) return 0.0;
        double totalPoints = enrollments.stream()
                .mapToDouble(e -> e.getGrade().getPoints() * e.getCourse().getCredits())
                .sum();
        int totalCredits = enrollments.stream().mapToInt(e -> e.getCourse().getCredits()).sum();
        return totalPoints / totalCredits;
    }
}