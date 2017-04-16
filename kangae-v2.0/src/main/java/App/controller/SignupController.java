package App.controller;

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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {
    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    public String signup() {
        if (userService.getLoggedInUser() == null) { // not logged in
            return "/signup";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/signup/teacher")
    public String signupTeacher(Model model) {
        if (userService.getLoggedInUser() == null) { // not logged in
            model.addAttribute("teacher", new Teacher());
            return "/signup_teacher";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup/teacher")
    public String signup(@ModelAttribute(value = "teacher") Teacher teacher) {
        if (userService.signup(teacher)) {
            return "redirect:/";
        } else {
            return "redirect:/signup/teacher"; // todo send error message
        }
    }

    @RequestMapping("/signup/student")
    public String signupStudent(Model model) {
        if (userService.getLoggedInUser() == null) { // not logged in
            model.addAttribute("student", new Student());
            return "/signup_student";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup/student")
    public String signup(@ModelAttribute(value = "student") Student student) {
        if (userService.signup(student)) {
            return "redirect:/";
        } else {
            return "redirect:/signup/student"; // todo send error message
        }
    }

}
