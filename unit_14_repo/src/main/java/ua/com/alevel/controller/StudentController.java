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
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;

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

    private List<SortData> generateSortDataList(String sort, String order) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", "id");
        map.put("firstName", "firstName");
        map.put("lastName", "lastName");
        map.put("email", "email");
        map.put("count of courses", "courses");
        map.put("details", null);
        map.put("delete", null);
        return generateSortDataList(map, sort, order);
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        return initPageDataAndReturnStudentsPage(studentFacade.findAll(webRequest), model);
    }

    @PostMapping
    public ModelAndView findAllAndRedirect(WebRequest webRequest, ModelMap modelMap) {
        return findAllAndRedirect(webRequest, modelMap, "students");
    }

    @GetMapping("/course/{courseId}")
    public String findAll(Model model, @PathVariable Integer courseId, WebRequest webRequest) {
        PageData<StudentResponseDto> pageData = studentFacade.findAllByCourseId(courseId, webRequest);
        pageData.setNewEntityRequest("/students/course/" + courseId + "/new");
        return initPageDataAndReturnStudentsPage(pageData, model);
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Integer id) {
        model.addAttribute("student", studentFacade.findById(id));
        return "pages/student/student_details";
    }

    @GetMapping("/course/{courseId}/new")
    public String redirectToNewStudentByCourseId(Model model, @PathVariable Integer courseId) {
        StudentRequestDto dto = new StudentRequestDto();
        dto.setCourseId(courseId);
        model.addAttribute("student", dto);
        model.addAttribute("requestUrl", "/students/new");
        return "pages/student/student_new";
    }

    @PostMapping("/new")
    public String createNewStudentByCourseId(@ModelAttribute StudentRequestDto student) {
        studentFacade.create(student);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdateStudentByCourseId(Model model, @PathVariable Integer id) {
        StudentResponseDto studentResponseDto = studentFacade.findById(id);
        StudentRequestDto dto = new StudentRequestDto();
        dto.setEmail(studentResponseDto.getEmail());
        dto.setFirstName(studentResponseDto.getFirstName());
        dto.setLastName(studentResponseDto.getLastName());
        model.addAttribute("student", dto);
        model.addAttribute("requestUrl", "/students/update/" + id);
        return "pages/student/student_new";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@ModelAttribute StudentRequestDto student, @PathVariable Integer id) {
        studentFacade.update(student, id);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        studentFacade.delete(id);
        return "redirect:/students";
    }

    private String initPageDataAndReturnStudentsPage(PageData<StudentResponseDto> pageData, Model model) {
        pageData.setTableName("All Students");
        pageData.setSearchRequest("/students");
        pageData.setSortDataList(generateSortDataList(pageData.getSort(), pageData.getOrder()));
        model.addAttribute("pageData", pageData);
        return "pages/student/student_all";
    }
}
