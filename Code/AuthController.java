package com.subclear.controller;

import com.subclear.model.User;
import com.subclear.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password,
                       HttpSession session,
                       Model model) {
        Optional<User> user = userService.login(username, password);
        if (user.isPresent()) {
            session.setAttribute("user", user.get());
            session.setAttribute("username", username);
            return "redirect:/";
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
    
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login?registered=true";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}
