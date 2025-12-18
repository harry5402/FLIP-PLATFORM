package com.flip.flip_platform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{
    @GetMapping("/")
    public String hoome()
    {
        return "home";
    }   
}
