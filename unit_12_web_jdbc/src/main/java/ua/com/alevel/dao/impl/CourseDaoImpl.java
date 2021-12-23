package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.CourseName;
import ua.com.alevel.store.ConnectionStoreFactory;
import ua.com.alevel.view.CourseViewDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseDaoImpl implements CourseDao {

    private final ConnectionStoreFactory storeFactory;

    private static final String FIND_ALL_VIEW_COURSES_QUERY = "select c.id, c.name, count(course_id) from courses as c\n" +
            "    left join students_courses as sc on c.id = sc.course_id\n" +
            "group by c.id";

    private static final String FIND_ALL_VIEW_COURSES_BY_STUDENT_QUERY = "select c.id, c.name, count(course_id) from courses as c\n" +
            "    left join students_courses as sc on c.id = sc.course_id\n" +
            "where sc.student_id = ?\n" +
            "group by c.id;";

    public CourseDaoImpl(ConnectionStoreFactory storeFactory) {
        this.storeFactory = storeFactory;
    }

    @Override
    public void create(Course entity) {

    }

    @Override
    public void update(Course entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public boolean existById(Integer id) {
        return false;
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public List<CourseViewDto> findAllPrepareView() {
        List<CourseViewDto> courses = new ArrayList<>();
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(FIND_ALL_VIEW_COURSES_QUERY)) {
            while (rs.next()) {
                courses.add(convertResultSetToCourseViewDto(rs));
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return courses;
    }

    @Override
    public List<CourseViewDto> findAllPrepareViewByStudent(int id) {
        List<CourseViewDto> courses = new ArrayList<>();
        try(PreparedStatement ps = storeFactory.getConnection().prepareStatement(FIND_ALL_VIEW_COURSES_BY_STUDENT_QUERY)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    courses.add(convertResultSetToCourseViewDto(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return courses;
    }

    private CourseViewDto convertResultSetToCourseViewDto(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int count = rs.getInt("count(course_id)");

        CourseViewDto course = new CourseViewDto();
        course.setId(id);
        course.setName(CourseName.valueOf(name));
        course.setCountOfStudents(count);

        return course;
    }
}
