package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private List<Enrollment> enrolledCourses;

    public Student(int id, String regNo, String fullName, String email, boolean active, List<Enrollment> enrolledCourses, LocalDate dateOfBirth) {
        super(id, regNo, fullName, email, active, dateOfBirth);
        this.enrolledCourses = enrolledCourses != null ? enrolledCourses : new ArrayList<>();
    }

    public List<Enrollment> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    public void addEnrollment(Enrollment enrollment) {
        enrolledCourses.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrolledCourses.remove(enrollment);
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Info: " + this);
    }

    @Override
    public String toString() {
        return "Student{" +
                "enrolledCourses=" + enrolledCourses +
                "} " + super.toString();
    }
}