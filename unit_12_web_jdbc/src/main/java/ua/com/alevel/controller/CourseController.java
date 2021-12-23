package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.view.CourseViewDto;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseDao courseDao;

    public CourseController(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @GetMapping
    public String findAll(Model model) {
        List<CourseViewDto> courses = courseDao.findAllPrepareView();
        model.addAttribute("courses", courses);
        return "pages/courses/courses_all";
    }

    @GetMapping("/{studentId}")
    public String findAllByStudent(Model model, @PathVariable int studentId) {
        List<CourseViewDto> courses = courseDao.findAllPrepareViewByStudent(studentId);
        model.addAttribute("courses", courses);
        return "pages/courses/courses_all";
    }
}
