# Campus Course & Records Manager (CCRM)

## Overview

The Campus Course & Records Manager (CCRM) is a console-based Java application built with Java SE to manage students, courses, enrollments, grades, and file operations for an educational institute. It demonstrates Object-Oriented Programming (OOP) principles (Encapsulation, Inheritance, Abstraction, Polymorphism), NIO.2 for file operations, Java Streams API, modern Date/Time API, design patterns (Singleton, Builder), custom exceptions, and a menu-driven CLI interface.

## Features

* **Student Management**: Add, list, update, deactivate students, and print profiles/transcripts.
* **Course Management**: Add, list, update, deactivate, and search courses by department, instructor, or semester.
* **Enrollment & Grading**: Enroll/unenroll students in courses, record marks, compute letter grades and GPA.
* **File Operations**: Import/export data in CSV format, create timestamped backups, and calculate backup directory size recursively.
* **Reports**: Generate GPA distribution reports using Streams.

## Project Structure

```
CCRM/
├── src/
│   ├── edu/
│   │   ├── ccrm/
│   │   │   ├── cli/
│   │   │   │   └── Main.java
│   │   │   ├── config/
│   │   │   │   └── AppConfig.java
│   │   │   ├── domain/
│   │   │   │   ├── Course.java
│   │   │   │   ├── CourseCode.java
│   │   │   │   ├── Enrollment.java
│   │   │   │   ├── Grade.java
│   │   │   │   ├── Instructor.java
│   │   │   │   ├── Person.java
│   │   │   │   ├── Persistable.java
│   │   │   │   └── Student.java
│   │   │   ├── io/
│   │   │   │   ├── BackupService.java
│   │   │   │   └── ImportExportService.java
│   │   │   ├── service/
│   │   │   │   ├── CourseService.java
│   │   │   │   ├── EnrollmentService.java
│   │   │   │   ├── StudentService.java
│   │   │   │   └── TranscriptService.java
│   │   │   └── util/
│   │   │       ├── DuplicateEnrollmentException.java
│   │   │       ├── MaxCreditLimitExceededException.java
│   │   │       └── RecursiveUtil.java
└── README.md
```

## Prerequisites

* **JDK 17+**: Install from oracle.com/java/technologies/downloads.
* **Visual Studio Code**: Install from code.visualstudio.com with the Java Extension Pack.
* **Git**: Install from git-scm.com for version control (optional).

## Setup Instructions

### 1. Install Java

* Download and install JDK 17+.
* Set `JAVA_HOME` to the JDK path (e.g., `C:\Program Files\Java\jdk-17` on Windows).
* Add `%JAVA_HOME%\bin` to your `PATH` environment variable.
* Verify installation:

  ```bash
  java -version
  ```
* Save the output as `screenshots/java-version.png`.

### 2. Set Up VS Code

* Open VS Code and select **File > Open Folder**, choosing the `CCRM` folder.
* Install the **Java Extension Pack**:

  * Go to Extensions (`Ctrl+Shift+X` or `Cmd+Shift+X`).
  * Search for *Java Extension Pack* by Microsoft and install.
* Create `.vscode/settings.json`:

  ```json
  {
      "java.project.sourcePaths": ["src"],
      "java.project.outputPath": "bin",
      "java.project.referencedLibraries": []
  }
  ```
* Save screenshot as `screenshots/vscode-setup.png`.

### 3. Clone or Create the Project

* Clone the repository (if hosted on GitHub):

  ```bash
  git clone <repository-url>
  cd CCRM
  ```
* Alternatively, create the project structure manually and copy provided source files under `src/edu/ccrm/`.
* Create a `data` folder in the root for runtime file operations.
* Copy sample CSV data:

  ```csv
  ID,RegNo,FullName,Email,DOB
  1,REG001,John Doe,john@example.com,2000-01-01
  2,REG002,Jane Smith,jane@example.com,2001-02-02
  ```

### 4. Compile and Run

* Open a terminal in the `CCRM` folder.
* Compile:

  ```bash
  javac -d bin src/edu/ccrm/cli/Main.java
  ```
* Run with assertions enabled:

  ```bash
  java -ea -cp bin edu.ccrm.cli.Main
  ```
* Alternatively, run from VS Code by clicking **Run** in `Main.java`.
* Capture CLI output as `screenshots/program-run.png`.

### 5. Perform a Backup

* In CLI, select option 6 to create a backup.
* Check `data/backups/` for a timestamped folder.
* Capture as `screenshots/backup-folder.png`.

## Running the Application

The app starts with:

```
Welcome to Campus Course & Records Manager (CCRM)
Data folder: data
```

**Menu:**

```
1. Manage Students
2. Manage Courses
3. Manage Enrollments
4. Manage Grades
5. Import/Export Data
6. Backup & Show Backup Size
7. Reports
8. Exit
```

## Evolution of Java

* **1995**: Java 1.0 released.
* **2004**: Java 5 introduced generics, enums, annotations.
* **2014**: Java 8 added lambdas, Streams, Date/Time API.
* **2021**: Java 17 (LTS) added sealed classes, pattern matching.

## Java ME vs SE vs EE

| Platform | Description                                                |
| -------- | ---------------------------------------------------------- |
| Java ME  | Micro Edition for constrained devices (mobiles, embedded). |
| Java SE  | Standard Edition for desktop/server apps (used here).      |
| Java EE  | Enterprise Edition for distributed, large-scale systems.   |

## JDK, JRE, JVM

* **JDK**: Development kit with compiler, debugger.
* **JRE**: Runtime environment for execution.
* **JVM**: Executes bytecode.
* Interaction: JDK compiles → JRE provides libs → JVM executes.

## Syllabus Mapping

| Topic             | File/Class/Method                                                                 |
| ----------------- | --------------------------------------------------------------------------------- |
| Encapsulation     | domain/Person.java (private fields, getters/setters)                              |
| Inheritance       | domain/Student.java, domain/Instructor.java extend Person                         |
| Abstraction       | domain/Person.java (abstract class, displayInfo)                                  |
| Polymorphism      | service/TranscriptService.java (toString overrides)                               |
| Singleton Pattern | config/AppConfig.java (getInstance)                                               |
| Builder Pattern   | domain/Course.java (inner Builder class)                                          |
| Custom Exceptions | util/DuplicateEnrollmentException.java, util/MaxCreditLimitExceededException.java |
| Streams API       | service/CourseService.java (searchCoursesByDepartment)                            |
| NIO.2             | io/BackupService.java (backup), io/ImportExportService.java (import/export)       |
| Date/Time API     | domain/Enrollment.java (enrollmentDate)                                           |
| Loops & Decisions | cli/Main.java (switch, while, for loops)                                          |
| Assertions        | service/StudentService.java (ID validation)                                       |

### Enabling Assertions

Run with:

```bash
java -ea -cp bin edu.ccrm.cli.Main
```

## Screenshots
<img width="1919" height="902" alt="Screenshot 2025-09-19 212615" src="https://github.com/user-attachments/assets/08958496-3a3e-4dd5-8c83-28f52fad88cf" />

<img width="1919" height="962" alt="image" src="https://github.com/user-attachments/assets/c88a3551-e5a4-4a3d-bb51-152120511bcf" />



## Sample Data

`test-data/students.csv`:

```csv
ID,RegNo,FullName,Email,DOB
1,REG001,John Doe,john@example.com,2000-01-01
2,REG002,Jane Smith,jane@example.com,2001-02-02
```

