package edu.ccrm.domain;

import java.time.LocalDateTime;

public class Enrollment {
    private Student student;
    private Course course;
    private LocalDateTime enrollmentDate;
    private double marks;
    private Grade grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDateTime.now();
        this.marks = 0;
        this.grade = Grade.F;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "course=" + course.getCode() +
                ", marks=" + marks +
                ", grade=" + grade +
                '}';
    }
}