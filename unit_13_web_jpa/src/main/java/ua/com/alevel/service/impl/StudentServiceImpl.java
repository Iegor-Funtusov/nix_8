package ua.com.alevel.service.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Student;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.service.StudentService;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void create(Student entity) {
        studentDao.create(entity);
    }

    @Override
    public void update(Student entity) {
        checkByExist(entity.getId());
        studentDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        checkByExist(id);
        studentDao.delete(id);
    }

    @Override
    public Student findById(Integer id) {
        checkByExist(id);
        return studentDao.findById(id).get();
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setEntities(studentDao.findAll(request));
        if (MapUtils.isNotEmpty(request.getQueryMap())) {
            Object o = request.getQueryMap().get("courseId");
            if (Objects.nonNull(o)) {
                try {
                    Integer courseId = (Integer) o;
                    dataTableResponse.setCount(studentDao.countByCourseId(courseId));
                } catch (Exception e) {
                    dataTableResponse.setCount(studentDao.count());
                }
            }
        } else {
            dataTableResponse.setCount(studentDao.count());
        }
        return dataTableResponse;
    }

    private void checkByExist(Integer id) {
        if (!studentDao.existById(id)) {
            throw new EntityExistException("entity not found");
        }
    }
}
