package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.view.StudentViewDto;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentDao studentDao;

    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @GetMapping
    public String findAll(Model model) {
        List<StudentViewDto> students = studentDao.findAllPrepareView();
        model.addAttribute("students", students);
        return "pages/students/students_all";
    }

    @GetMapping("/{courseId}")
    public String findAll(Model model, @PathVariable int courseId) {
        List<StudentViewDto> students = studentDao.findAllPrepareViewByCourse(courseId);
        model.addAttribute("students", students);
        return "pages/students/students_all";
    }
}
