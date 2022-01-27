package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.CourseName;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseDao courseDao;

    public CourseController(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("courses", courseDao.findAll());
        return "course/course_all";
    }

    @GetMapping("/new")
    public String redirectToNew(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("courseNames", CourseName.values());
        return "course/course_new";
    }

    @PostMapping("/new")
    public String newCourse(@ModelAttribute Course course) {
        courseDao.create(course);
        return "redirect:/courses";
    }
}
