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
        if (!userService.isLoggedIn()) {
            return "/signup";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/signup/teacher")
    public String signupTeacher(Model model) {
        if (!userService.isLoggedIn()) {
            if (!model.containsAttribute("teacher"))
                model.addAttribute("teacher", new Teacher());
            return "/signup_teacher";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup/teacher")
    public String signup(@ModelAttribute(value = "teacher") Teacher teacher, Model model) {
        if (userService.signup(teacher)) {
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Incorrect data!");
            return signupTeacher(model);
        }
    }

    @RequestMapping("/signup/student")
    public String signupStudent(Model model) {
        if (!userService.isLoggedIn()) {
            if (!model.containsAttribute("student"))
                model.addAttribute("student", new Student());
            return "/signup_student";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup/student")
    public String signup(@ModelAttribute(value = "student") Student student, Model model) {
        if (userService.signup(student)) {
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Incorrect data!");
            return signupStudent(model);
        }
    }

}
