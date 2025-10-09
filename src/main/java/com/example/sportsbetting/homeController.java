package com.example.sportsbetting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {
    
    @GetMapping("/")
    public String home() {
        return "25-26 NBA season";
    }
}