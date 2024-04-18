package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("")
    public String getMethodName(HttpServletRequest request) {
        String role = request.getHeader("role");
        if (role.equals("USER")) {
            // Implementation when role is USER
            return "Welcome user";
        }

        // Implementation when role is ADMIN
        return "Welcome admin";
    }
}
