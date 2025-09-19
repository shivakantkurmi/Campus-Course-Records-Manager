// package edu.ccrm.cli;

// import edu.ccrm.config.AppConfig;
// import edu.ccrm.domain.*;
// import edu.ccrm.domain.enums.Semester;
// import edu.ccrm.domain.enums.Grade;
// import edu.ccrm.service.StudentService;
// import edu.ccrm.service.CourseService;

// import java.util.Scanner;

// public class CLI {
//     private final Scanner sc = new Scanner(System.in);
//     private final StudentService studentService = new StudentService();
//     private final CourseService courseService = new CourseService();

//     public void start() {
//         System.out.println("CCRM Console Started");
//         AppConfig config = AppConfig.getInstance();
//         System.out.println("Data folder: " + config.getDataFolder());

//         boolean exit = false;
//         while(!exit) {
//             System.out.println("\n1. Manage Students\n2. Manage Courses\n3. Exit");
//             System.out.print("Select: ");
//             int choice = sc.nextInt();
//             sc.nextLine();
//             switch(choice) {
//                 case 1: manageStudents(); break;
//                 case 2: manageCourses(); break;
//                 case 3: exit = true; break;
//                 default: System.out.println("Invalid choice");
//             }
//         }
//     }

//     private void manageStudents() {
//         System.out.println("\n1. Add Student\n2. List Students");
//         int choice = sc.nextInt();
//         sc.nextLine();
//         switch(choice) {
//             case 1:
//                 System.out.print("ID: "); String id = sc.nextLine();
//                 System.out.print("RegNo: "); String reg = sc.nextLine();
//                 System.out.print("Name: "); String name = sc.nextLine();
//                 System.out.print("Email: "); String email = sc.nextLine();
//                 Student s = new Student(id, reg, name, email);
//                 studentService.addStudent(s);
//                 System.out.println("Student added!");
//                 break;
//             case 2:
//                 studentService.getAllStudents().forEach(Student::printProfile);
//                 break;
//         }
//     }

//     private void manageCourses() {
//         System.out.println("\n1. Add Course\n2. List Courses");
//         int choice = sc.nextInt();
//         sc.nextLine();
//         switch(choice) {
//             case 1:
//                 System.out.print("Code: "); String code = sc.nextLine();
//                 System.out.print("Title: "); String title = sc.nextLine();
//                 System.out.print("Credits: "); int credits = sc.nextInt(); sc.nextLine();
//                 Course c = new Course.Builder()
//                         .code(code)
//                         .title(title)
//                         .credits(credits)
//                         .semester(Semester.FALL)
//                         .build();
//                 courseService.addCourse(c);
//                 System.out.println("Course added!");
//                 break;
//             case 2:
//                 courseService.getAllCourses().forEach(System.out::println);
//                 break;
//         }
//     }

//     public static void main(String[] args) {
//         new CLI().start();
//     }
// }
