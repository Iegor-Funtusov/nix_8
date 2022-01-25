package ua.com.alevel.service.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Student;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.repository.StudentRepository;
import ua.com.alevel.service.StudentService;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void create(Student entity) {
        studentRepository.save(entity);
    }

    @Override
    public void update(Student entity) {
        checkByExist(entity.getId());
        studentRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        checkByExist(id);
        studentRepository.deleteById(id);
    }

    @Override
    public Student findById(Integer id) {
        checkByExist(id);
        return studentRepository.findById(id).get();
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setEntities(studentRepository.findAll());
        dataTableResponse.setCount(studentRepository.count());
        if (MapUtils.isNotEmpty(request.getQueryMap())) {
            Object o = request.getQueryMap().get("courseId");
            if (Objects.nonNull(o)) {
                try {
                    Integer courseId = (Integer) o;
                    dataTableResponse.setCount(studentRepository.countByCourses(courseId));
                } catch (Exception e) {
                    dataTableResponse.setCount(studentRepository.count());
                }
            }
        }
        return dataTableResponse;
    }

    private void checkByExist(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityExistException("entity not found");
        }
    }
}
