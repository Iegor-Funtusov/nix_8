package ua.com.alevel.final_project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String navigateToBooks() {
        return "redirect:/books";
    }
}
