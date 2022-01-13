package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.entity.Student;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.service.StudentService;

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
    public List<StudentResponseDto> findByCourseId(Integer courseId) {
        List<Student> students = studentService.findByCourseId(courseId);
        return students.stream().map(StudentResponseDto::new).collect(Collectors.toList());
    }
}
