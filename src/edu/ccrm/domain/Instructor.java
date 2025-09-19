package edu.ccrm.domain;

import java.time.LocalDate;

public class Instructor extends Person {
    public Instructor(int id, String regNo, String fullName, String email, boolean active, LocalDate dateOfBirth) {
        super(id, regNo, fullName, email, active, dateOfBirth);
    }

    @Override
    public void displayInfo() {
        System.out.println("Instructor Info: " + this);
    }
}