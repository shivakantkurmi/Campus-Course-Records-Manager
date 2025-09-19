package edu.ccrm.domain;

import java.time.LocalDate;

public abstract class Person {
    private int id;
    private String regNo;
    private String fullName;
    private String email;
    private boolean active;
    private LocalDate dateOfBirth;

    public Person(int id, String regNo, String fullName, String email, boolean active, LocalDate dateOfBirth) {
        this.id = id;
        this.regNo = regNo;
        this.fullName = fullName;
        this.email = email;
        this.active = active;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public abstract void displayInfo();

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", regNo='" + regNo + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}