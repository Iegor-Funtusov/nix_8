package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.entity.Student;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebRequestUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;

    public StudentFacadeImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setEmail(studentRequestDto.getEmail());
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        studentService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, Integer id) {
        Student student = studentService.findById(id);
        student.setEmail(studentRequestDto.getEmail());
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        studentService.update(student);
    }

    @Override
    public void delete(Integer id) {
        studentService.delete(id);
    }

    @Override
    public StudentResponseDto findById(Integer id) {
        return new StudentResponseDto(studentService.findById(id));
    }

    @Override
    public List<StudentResponseDto> findAll() {
        List<Student> students = studentService.findAll();
        return students.stream().map(StudentResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public PageData<StudentResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(request);
        DataTableResponse<Student> dataTableResponse = studentService.findAll(dataTableRequest);

        List<StudentResponseDto> dtos = dataTableResponse.
                getEntities().
                stream().
                map(StudentResponseDto::new).
                toList();
        PageData<StudentResponseDto> pageData = new PageData<>(dtos);
        pageData.setCurrentPage(dataTableRequest.getPage());
        pageData.setPageSize(dataTableRequest.getSize());
        pageData.setSort(dataTableRequest.getSort());
        pageData.setOrder(dataTableRequest.getOrder());
        pageData.initPageDataState(dataTableResponse);
        return pageData;
    }

    @Override
    public List<StudentResponseDto> findByCourseId(Integer courseId) {
        List<Student> students = studentService.findByCourseId(courseId);
        return students.stream().map(StudentResponseDto::new).collect(Collectors.toList());
    }
}
