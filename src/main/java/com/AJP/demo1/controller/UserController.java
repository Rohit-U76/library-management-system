package com.AJP.demo1.controller;

import com.AJP.demo1.entity.User;
import com.AJP.demo1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // List Users
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users"; // resources/templates/users.html
    }

    // Show Add/Update Form
    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isNew", true);
        return "user-form"; // resources/templates/user-form.html
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("isNew", false);
        return "user-form";
    }

    // Process Form Submission
    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isNew", user.getId() == null);
            return "user-form";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    // Delete User
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}