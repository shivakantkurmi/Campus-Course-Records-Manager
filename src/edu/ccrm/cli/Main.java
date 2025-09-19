package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Person;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.TranscriptService;
import edu.ccrm.config.AppConfig;
import edu.ccrm.util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final TranscriptService transcriptService = new TranscriptService();
    private static final ImportExportService importExportService = new ImportExportService();
    private static final BackupService backupService = new BackupService();

    public static void main(String[] args) {
        AppConfig config = AppConfig.getInstance();
        System.out.println("Welcome to Campus Course & Records Manager (CCRM)");
        System.out.println("Data folder: " + config.getDataFolder());

        // Print platform note
        System.out.println("\nJava SE: Standard Edition for desktop/server apps.");
        System.out.println("Java ME: Micro Edition for embedded devices.");
        System.out.println("Java EE: Enterprise Edition for large-scale apps.");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter choice: ");
            switch (choice) {
                case 1 -> manageStudents();
                case 2 -> manageCourses();
                case 3 -> manageEnrollments();
                case 4 -> manageGrades();
                case 5 -> importExportData();
                case 6 -> backupAndShowSize();
                case 7 -> generateReports();
                case 8 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
        System.out.println("Exiting CCRM.");
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments");
        System.out.println("4. Manage Grades");
        System.out.println("5. Import/Export Data");
        System.out.println("6. Backup & Show Backup Size");
        System.out.println("7. Reports");
        System.out.println("8. Exit");
    }

    private static void manageStudents() {
        System.out.println("\nStudent Management:");
        System.out.println("1. Add Student");
        System.out.println("2. List Students");
        System.out.println("3. Update Student");
        System.out.println("4. Deactivate Student");
        System.out.println("5. Print Student Profile");
        int choice = getIntInput("Enter choice: ");
        switch (choice) {
            case 1 -> addStudent();
            case 2 -> listStudents();
            case 3 -> updateStudent();
            case 4 -> deactivateStudent();
            case 5 -> printStudentProfile();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void addStudent() {
        int id = getIntInput("ID: ");
        String regNo = getStringInput("Reg No: ");
        String fullName = getStringInput("Full Name: ");
        String email = getStringInput("Email: ");
        LocalDate dob = LocalDate.parse(getStringInput("DOB (YYYY-MM-DD): "));
        Student student = new Student(id, regNo, fullName, email, true, new ArrayList<>(), dob);
        studentService.addStudent(student);
        System.out.println("Student added.");
    }

    private static void listStudents() {
        studentService.getStudents().forEach(System.out::println);
    }

    private static void updateStudent() {
        int id = getIntInput("Student ID: ");
        Student student = studentService.getStudentById(id);
        if (student != null) {
            String newEmail = getStringInput("New Email: ");
            student.setEmail(newEmail);
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deactivateStudent() {
        int id = getIntInput("Student ID: ");
        Student student = studentService.getStudentById(id);
        if (student != null) {
            student.setActive(false);
            System.out.println("Student deactivated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void printStudentProfile() {
        int id = getIntInput("Student ID: ");
        Student student = studentService.getStudentById(id);
        if (student != null) {
            System.out.println(student);
            System.out.println(transcriptService.generateTranscript(student));
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void manageCourses() {
        System.out.println("\nCourse Management:");
        System.out.println("1. Add Course");
        System.out.println("2. List Courses");
        System.out.println("3. Update Course");
        System.out.println("4. Deactivate Course");
        System.out.println("5. Search Courses");
        int choice = getIntInput("Enter choice: ");
        switch (choice) {
            case 1 -> addCourse();
            case 2 -> listCourses();
            case 3 -> updateCourse();
            case 4 -> deactivateCourse();
            case 5 -> searchCourses();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void addCourse() {
        String code = getStringInput("Code: ");
        String title = getStringInput("Title: ");
        int credits = getIntInput("Credits: ");
        String instructorName = getStringInput("Instructor Name: ");
        Instructor instructor = new Instructor(0, "", instructorName, "", true, LocalDate.now()); // Dummy
        Semester semester = Semester.valueOf(getStringInput("Semester (SPRING/SUMMER/FALL): ").toUpperCase());
        String department = getStringInput("Department: ");
        Course course = new Course.Builder()
                .code(code)
                .title(title)
                .credits(credits)
                .instructor(instructor)
                .semester(semester)
                .department(department)
                .build();
        courseService.addCourse(course);
        System.out.println("Course added.");
    }

    private static void listCourses() {
        courseService.getCourses().forEach(System.out::println);
    }

    private static void updateCourse() {
        String code = getStringInput("Course Code: ");
        Course course = courseService.getCourseByCode(code);
        if (course != null) {
            String newTitle = getStringInput("New Title: ");
            course.setTitle(newTitle);
            System.out.println("Course updated.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void deactivateCourse() {
        String code = getStringInput("Course Code: ");
        Course course = courseService.getCourseByCode(code);
        if (course != null) {
            courseService.getCourses().remove(course);
            System.out.println("Course deactivated.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void searchCourses() {
        String department = getStringInput("Department: ");
        courseService.searchCoursesByDepartment(department).forEach(System.out::println);
    }

    private static void manageEnrollments() {
        System.out.println("\nEnrollment Management:");
        System.out.println("1. Enroll Student");
        System.out.println("2. Unenroll Student");
        int choice = getIntInput("Enter choice: ");
        switch (choice) {
            case 1 -> enrollStudent();
            case 2 -> unenrollStudent();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void enrollStudent() {
        int studentId = getIntInput("Student ID: ");
        String courseCode = getStringInput("Course Code: ");
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseByCode(courseCode);
        if (student != null && course != null) {
            try {
                enrollmentService.enroll(student, course);
                System.out.println("Enrolled.");
            } catch (MaxCreditLimitExceededException | DuplicateEnrollmentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Not found.");
        }
    }

    private static void unenrollStudent() {
        int studentId = getIntInput("Student ID: ");
        String courseCode = getStringInput("Course Code: ");
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseByCode(courseCode);
        if (student != null && course != null) {
            enrollmentService.unenroll(student, course);
            System.out.println("Unenrolled.");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void manageGrades() {
        int studentId = getIntInput("Student ID: ");
        String courseCode = getStringInput("Course Code: ");
        double marks = getDoubleInput("Marks: ");
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseByCode(courseCode);
        if (student != null && course != null) {
            Enrollment enrollment = enrollmentService.getEnrollment(student, course);
            if (enrollment != null) {
                enrollment.setMarks(marks);
                enrollment.setGrade(Grade.fromMarks(marks));
                System.out.println("Grade recorded.");
            } else {
                System.out.println("Enrollment not found.");
            }
        } else {
            System.out.println("Not found.");
        }
    }

    private static void importExportData() {
        System.out.println("\nImport/Export:");
        System.out.println("1. Import Students");
        System.out.println("2. Export Students");
        int choice = getIntInput("Enter choice: ");
        switch (choice) {
            case 1 -> {
                try {
                    importExportService.importStudents("students.csv");
                    System.out.println("Imported.");
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case 2 -> {
                try {
                    importExportService.exportStudents("students.csv");
                    System.out.println("Exported.");
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            default -> System.out.println("Invalid.");
        }
    }

    private static void backupAndShowSize() {
        try {
            backupService.backupData();
            System.out.println("Backup created.");
            Path backupDir = Paths.get(AppConfig.getInstance().getDataFolder(), "backups");
            long size = RecursiveUtil.calculateDirectorySize(backupDir);
            System.out.println("Backup size: " + size + " bytes");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void generateReports() {
        List<Student> students = studentService.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(s -> transcriptService.calculateGPA(s)))
                .forEach((gpa, list) -> System.out.println("GPA " + gpa + ": " + list.size() + " students"));
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}