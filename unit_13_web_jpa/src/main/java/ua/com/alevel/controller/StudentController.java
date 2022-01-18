package ua.com.alevel.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;

import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        PageData<StudentResponseDto> pageData = studentFacade.findAll(webRequest);
        System.out.println("pageData = " + pageData);
        model.addAttribute("pageData", pageData);
        return "pages/student/student_all";
    }

    @PostMapping
    public ModelAndView findAllAndRedirect(WebRequest webRequest, ModelMap modelMap) {
        Map<String, String[]> parameterMap = webRequest.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(modelMap::addAttribute);
        }
        return new ModelAndView("redirect:/students", modelMap);
    }

    @GetMapping("/course/{courseId}")
    public String findAll(Model model, @PathVariable Integer courseId) {
        model.addAttribute("students", studentFacade.findByCourseId(courseId));
        return "pages/student/student_all";
    }
}
