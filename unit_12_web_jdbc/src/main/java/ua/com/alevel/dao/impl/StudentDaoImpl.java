package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;
import ua.com.alevel.store.ConnectionStoreFactory;
import ua.com.alevel.view.StudentViewDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentDaoImpl implements StudentDao {

    private final ConnectionStoreFactory storeFactory;

    public StudentDaoImpl(ConnectionStoreFactory storeFactory) {
        this.storeFactory = storeFactory;
    }

    private static final String CREATE_STUDENT_QUERY = "insert into students values (default,?,?)";
    private static final String UPDATE_STUDENT_QUERY = "update students set first_name = ?, last_name = ? where id = ?";
    private static final String DELETE_STUDENT_QUERY = "delete from students where id = ?";
    private static final String EXIST_STUDENT_BY_ID_QUERY = "select count(*) as exist from students where id = ";
    private static final String FIND_STUDENT_BY_ID_QUERY = "select * from students where id = ";
    private static final String FIND_ALL_STUDENT__QUERY = "select * from students";
    private static final String FIND_ALL_VIEW_STUDENT__QUERY = "select s.id, s.first_name, s.last_name, count(student_id) as count_of_courses \n" +
            "from students as s \n" +
            "         left join students_courses as sc on s.id = sc.student_id \n" +
            "group by s.id";

    @Override
    public void create(Student entity) {
        try (PreparedStatement ps = storeFactory.getConnection().prepareStatement(CREATE_STUDENT_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
    }

    @Override
    public void update(Student entity) {
        try (PreparedStatement ps = storeFactory.getConnection().prepareStatement(UPDATE_STUDENT_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement ps = storeFactory.getConnection().prepareStatement(DELETE_STUDENT_QUERY)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Integer id) {
        long count = 0;
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(EXIST_STUDENT_BY_ID_QUERY + id)) {
            if (rs.next()) {
                count = rs.getLong("exist");
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Optional<Student> findById(Integer id) {
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(FIND_STUDENT_BY_ID_QUERY + id)) {
            if (rs.next()) {
                return Optional.of(convertResultSetToStudent(rs));
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(FIND_ALL_STUDENT__QUERY)) {
            while (rs.next()) {
                students.add(convertResultSetToStudent(rs));
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return students;
    }

    @Override
    public List<StudentViewDto> findAllPrepareView() {
        List<StudentViewDto> students = new ArrayList<>();
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(FIND_ALL_VIEW_STUDENT__QUERY)) {
            while (rs.next()) {
                students.add(convertResultSetToStudentViewDto(rs));
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return students;
    }

    private Student convertResultSetToStudent(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");

        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);

        return student;
    }

    private StudentViewDto convertResultSetToStudentViewDto(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        int countOfCourses = rs.getInt("count_of_courses");

        StudentViewDto student = new StudentViewDto();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setCountOfCourses(countOfCourses);

        return student;
    }
}
