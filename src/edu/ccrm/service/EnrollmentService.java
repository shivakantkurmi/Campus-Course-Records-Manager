package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.util.DuplicateEnrollmentException;
import edu.ccrm.util.MaxCreditLimitExceededException;

public class EnrollmentService {
    private static final int MAX_CREDITS_PER_SEMESTER = 20;

    public void enroll(Student student, Course course) throws MaxCreditLimitExceededException, DuplicateEnrollmentException {
        if (student.getEnrolledCourses().stream().anyMatch(e -> e.getCourse().getCode().equals(course.getCode()))) {
            throw new DuplicateEnrollmentException("Already enrolled.");
        }
        int currentCredits = student.getEnrolledCourses().stream()
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();
        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditLimitExceededException("Max credits exceeded.");
        }
        Enrollment enrollment = new Enrollment(student, course);
        student.addEnrollment(enrollment);
    }

    public void unenroll(Student student, Course course) {
        student.getEnrolledCourses().removeIf(e -> e.getCourse().getCode().equals(course.getCode()));
    }

    public Enrollment getEnrollment(Student student, Course course) {
        return student.getEnrolledCourses().stream()
                .filter(e -> e.getCourse().getCode().equals(course.getCode()))
                .findFirst().orElse(null);
    }
}