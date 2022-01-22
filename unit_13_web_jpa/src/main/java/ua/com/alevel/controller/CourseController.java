package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.cource.CourseRequestDto;
import ua.com.alevel.dto.cource.CourseResponseDto;
import ua.com.alevel.entity.CourseName;
import ua.com.alevel.facade.CourseFacade;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/courses")
public class CourseController extends AbstractController {

    private final CourseFacade courseFacade;

    public CourseController(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }

    private List<AbstractController.SortData> generateSortDataList(String sort, String order) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", "id");
        map.put("name", "name");
        map.put("count of students", "students");
        map.put("delete", null);
        return generateSortDataList(map, sort, order);
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        return initPageDataAndReturnCoursesPage(courseFacade.findAll(webRequest), model);
    }

    @GetMapping("/student/{studentId}")
    public String findAll(Model model, @PathVariable Integer studentId, WebRequest webRequest) {
        PageData<CourseResponseDto> pageData = courseFacade.findAllByStudentId(studentId, webRequest);
        return initPageDataAndReturnCoursesPage(pageData, model);
    }

    @PostMapping
    public ModelAndView findAllAndRedirect(WebRequest webRequest, ModelMap modelMap) {
        return findAllAndRedirect(webRequest, modelMap, "courses");
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

    private String initPageDataAndReturnCoursesPage(PageData<CourseResponseDto> pageData, Model model) {
        pageData.setTableName("All Courses");
        pageData.setSearchRequest("/courses");
        pageData.setNewEntityRequest("/courses/new");
        pageData.setSortDataList(generateSortDataList(pageData.getSort(), pageData.getOrder()));
        model.addAttribute("pageData", pageData);
        return "pages/course/course_all";
    }
}
