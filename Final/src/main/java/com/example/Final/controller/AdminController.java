package com.example.Final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/admin")
public class AdminController 
{
	@GetMapping("/")
    public String home() {
        return "admin/index";  // maps to templates/admin/index.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login"; // maps to templates/admin/login.html
    }

    @PostMapping("/login")
    public String processLogin(String username, String password) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "redirect:/admin/dashboard";
        }
        return "admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard"; // maps to templates/admin/dashboard.html
    }

    @GetMapping("/students")
    public String studentsPage() {
        return "admin/students"; // maps to templates/admin/students.html
    }

    @GetMapping("/rooms")
    public String roomsPage() {
        return "admin/rooms"; // maps to templates/admin/rooms.html
    }
}
