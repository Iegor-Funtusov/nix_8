package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.StudentFacade;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("students", studentFacade.findAll());
        return "pages/student/student_all";
    }

    @GetMapping("/course/{courseId}")
    public String findAll(Model model, @PathVariable Integer courseId) {
        model.addAttribute("students", studentFacade.findByCourseId(courseId));
        return "pages/student/student_all";
    }
}
