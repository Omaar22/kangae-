package App.controller;

import App.model.Comment;
import App.model.Student;
import App.model.Teacher;
import App.model.User;
import App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class SignupController {
    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    public String signup(Model model) {
        if (!model.containsAttribute("user"))
            model.addAttribute("user", new User());
        return "/signup";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup/teacher")
    public String signupTeacher(@ModelAttribute(value = "user") User user, Model model) {
        Teacher teacher = new Teacher(user);
        if (userService.signup(teacher)) {
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Incorrect data!");
            return signup(model);
        }
    }
    @RequestMapping(method = RequestMethod.POST, value = "/signup/student")
    public String signupStudent(@ModelAttribute(value = "user") User user, Model model) {
        Student student = new Student(user);
        if (userService.signup(student)) {
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Incorrect data!");
            return signup(model);
        }
    }

}
