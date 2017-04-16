package App.controller;

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
public class SigninController {
    @Autowired
    private UserService userService;

    @RequestMapping("/signin")
    public String signin(Model model) {
        if (userService.getLoggedInUser() == null) { // not logged in
            model.addAttribute("user", new User());
            return "/signin";
        } else {
            return "redirect:/"; // todo: return 'already logged in' message
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public String signin(@ModelAttribute(value = "user") User user, Model model) {
        if (userService.signin(user.getEmail(), user.getPassword()) == null) {
            model.addAttribute("user", new User());
            return "redirect:/signin"; // todo: return error message
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/signout")
    public String signout() {
        userService.signOut();
        return "redirect:/";
    }

}