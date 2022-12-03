package com.htpt.coordinator.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class index {
    
    @GetMapping("/index")
    public String loginGet() {

        return "index";
    }
}
