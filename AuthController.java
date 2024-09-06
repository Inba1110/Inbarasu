package net.enjoy.springboot.registrationlogin.controller;

import jakarta.validation.Valid;
import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    // @GetMapping("/index2")
    // public String home() {
    //     return "index2";
    // }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
//     @GetMapping("/std-login")
// public String stdlogin() {
//     return " New folder (2)/Responsive-Navbar-main/Login-pages/std-login"; // Replace "dashboard" with the name of your dashboard view template
// }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/StdDashboard")
    public String stdDashboard() {
        return "StdDashboard"; // Return the corresponding HTML template
    }

    @GetMapping("/StdNotification")
    public String stdNotification() {
        return "StdNotification"; // Return the corresponding HTML template
    }

    @GetMapping("/StdAccount")
    public String stdAccount() {
        return "StdAccount"; // Return the corresponding HTML template
    }

    @GetMapping("/StdTeacher")
    public String stdTeacher() {
        return "StdTeacher"; // Return the corresponding HTML template
    }

    @GetMapping("/TeacherDashboard")
    public String teacherDashboard() {
        return "TeacherDashboard"; // Return the corresponding HTML template
    }

    @GetMapping("/ExploreTeacher")
    public String exploreTeacher() {
        return "ExploreTeacher"; // Return the corresponding HTML template
    }
    @GetMapping("/schedule")
    public String schedule() {
        return "schedule"; // Return the corresponding HTML template
    }
}