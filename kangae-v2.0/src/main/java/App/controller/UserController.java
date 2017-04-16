package App.controller;

import App.model.Student;
import App.model.Teacher;
import App.model.User;
import App.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import App.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private StudentService studentService;


    @RequestMapping("/Hi")
    public Iterable<Student> get() {
        return studentService.getAll();
    }

}
