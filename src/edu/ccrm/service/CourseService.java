package edu.ccrm.service;

import edu.ccrm.domain.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseService {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public Course getCourseByCode(String code) {
        return courses.stream().filter(c -> c.getCode().equals(code)).findFirst().orElse(null);
    }

    public List<Course> searchCoursesByDepartment(String department) {
        return courses.stream()
                .filter(c -> c.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }
}