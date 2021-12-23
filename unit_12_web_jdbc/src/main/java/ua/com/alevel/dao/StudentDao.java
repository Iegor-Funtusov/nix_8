package ua.com.alevel.dao;

import ua.com.alevel.entity.Student;
import ua.com.alevel.view.StudentViewDto;

import java.util.List;

public interface StudentDao extends BaseDao<Student> {

    List<StudentViewDto> findAllPrepareViewByCourse(int id);
}
