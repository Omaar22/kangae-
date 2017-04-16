package App.controller;

import App.model.Course;
import App.model.Teacher;
import App.model.User;
import App.service.CourseService;
import App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CourseController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseservice;

    @RequestMapping("/courses")
    public String courses(Model model) {
        ArrayList<Course> all = courseservice.getALLCourses();
        model.addAttribute("courses", all);
        return "/courses";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addcourse")
    public String addCourse(@ModelAttribute(value = "course") Course course) {
        // todo: check
        course.setTeacher((Teacher) userService.getLoggedInUser());
        courseservice.addCourse(course);
        return "redirect:/";
    }

    @RequestMapping(value = "/course/create")
    public String addCourse(Model model) {
        if (userService.getLoggedInUser() != null && userService.getLoggedInUser() instanceof Teacher) { // authorized
            model.addAttribute("course", new Course());
            return "/create_course";
        } else {
            return "redirect:/"; // todo: return 'Not Authorized' message
        }
    }


}


