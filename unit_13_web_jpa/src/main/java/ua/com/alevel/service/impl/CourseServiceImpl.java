package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Course;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.service.CourseService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void create(Course entity) {
        courseDao.create(entity);
    }

    @Override
    public void update(Course entity) {
        checkByExist(entity.getId());
        courseDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        checkByExist(id);
        courseDao.delete(id);
    }

    @Override
    public Course findById(Integer id) {
        checkByExist(id);
        return courseDao.findById(id).get();
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public DataTableResponse<Course> findAll(DataTableRequest request) {
        return null;
    }

    private void checkByExist(Integer id) {
        if (!courseDao.existById(id)) {
            throw new EntityExistException("entity not found");
        }
    }
}
