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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController extends AbstractController {

    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    private List<AbstractController.SortData> generateSortDataList(String sort, String order) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", "id");
        map.put("firstName", "firstName");
        map.put("lastName", "lastName");
        map.put("email", "email");
        map.put("count of courses", "courses");
        map.put("delete", null);
        return generateSortDataList(map, sort, order);
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        PageData<StudentResponseDto> pageData = studentFacade.findAll(webRequest);
        pageData.setTableName("All Students");
        pageData.setSearchRequest("/students");
        pageData.setNewEntityRequest("/students/new");
        pageData.setSortDataList(generateSortDataList(pageData.getSort(), pageData.getOrder()));
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
