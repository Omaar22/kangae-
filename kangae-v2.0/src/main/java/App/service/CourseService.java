package App.service;

import App.model.Course;

import App.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepo;

    public ArrayList<Course> getALLCourses() {
        ArrayList<Course> courses = (ArrayList<Course>) courseRepo.findAll();
        return courses;
    }

    public void addCourse(Course course) {
        courseRepo.save(course);
    }

    public Course getCourse(String courseName) {
        return courseRepo.findByName(courseName);
    }

    public Course updateCourse(int id, Course course) {
        return courseRepo.save(course);
    }

    public void deleteCourse(int id, Course course) {
        courseRepo.delete(course);
    }

    public ArrayList<Course> getCoursesByTeacher(String teacherEmail) {
        return courseRepo.findByTeacherEmail(teacherEmail);
    }
}


