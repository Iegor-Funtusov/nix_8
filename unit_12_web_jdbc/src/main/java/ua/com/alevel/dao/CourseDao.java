package ua.com.alevel.dao;

import ua.com.alevel.entity.Course;
import ua.com.alevel.view.CourseViewDto;

import java.util.List;

public interface CourseDao extends BaseDao<Course> {

    List<CourseViewDto> findAllPrepareViewByStudent(int id);
}
