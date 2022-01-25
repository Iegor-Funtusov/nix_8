package ua.com.alevel.service.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Course;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.repository.CourseRepository;
import ua.com.alevel.service.CourseService;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void create(Course entity) {
        courseRepository.save(entity);
    }

    @Override
    public void update(Course entity) {
        checkByExist(entity.getId());
        courseRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        checkByExist(id);
        courseRepository.deleteById(id);
    }

    @Override
    public Course findById(Integer id) {
        checkByExist(id);
        return courseRepository.findById(id).get();
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public DataTableResponse<Course> findAll(DataTableRequest request) {
        DataTableResponse<Course> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setEntities(courseRepository.findAll());
        dataTableResponse.setCount(courseRepository.count());
        if (MapUtils.isNotEmpty(request.getQueryMap())) {
            Object o = request.getQueryMap().get("studentId");
            if (Objects.nonNull(o)) {
                try {
                    Integer studentId = (Integer) o;
                    dataTableResponse.setCount(courseRepository.countByStudentId(studentId));
                } catch (Exception e) {
                    dataTableResponse.setCount(courseRepository.count());
                }
            }
        }
        return dataTableResponse;
    }

    private void checkByExist(Integer id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityExistException("entity not found");
        }
    }
}
