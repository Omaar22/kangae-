package App.repository;

import App.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    Course findByName(String name);

    ArrayList<Course> findByTeacherEmail(String teacherEmail);
}
