package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.dto.cource.CourseRequestDto;
import ua.com.alevel.dto.cource.CourseResponseDto;
import ua.com.alevel.entity.CourseName;
import ua.com.alevel.facade.CourseFacade;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseFacade courseFacade;

    public CourseController(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        List<CourseResponseDto> courses = courseFacade.findAll();
        model.addAttribute("courses", courses);
        return "pages/course/course_all";
    }

    @GetMapping("/new")
    public String redirectToCreateNewCourse(Model model) {
        model.addAttribute("course", new CourseRequestDto());
        model.addAttribute("courseTypes", CourseName.values());
        return "pages/course/course_new";
    }

    @PostMapping("/new")
    public String createNewCourse(@ModelAttribute("course") CourseRequestDto courseRequestDto) {
        courseFacade.create(courseRequestDto);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        courseFacade.delete(id);
        return "redirect:/courses";
    }
}
