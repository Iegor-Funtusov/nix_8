package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.CourseName;
import ua.com.alevel.entity.Student;

import java.util.Set;

@SpringBootApplication
public class Unit13WebJpaApplication {

    private final CourseDao courseDao;
    private final StudentDao studentDao;

    public Unit13WebJpaApplication(CourseDao courseDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(Unit13WebJpaApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void testDb() {
//        createCourse();
//        createStudent();
        link();
    }

    private void link() {
        Student student = studentDao.findById(1).get();
        Course course = courseDao.findById(1).get();
        Set<Student> students = course.getStudents();
        students.add(student);
        courseDao.update(course);
    }

    private void createCourse() {
        Course course = new Course();
        course.setName(CourseName.JAVA);
        courseDao.create(course);
    }

    private void createStudent() {
        Student student = new Student();
        student.setFirstName("f");
        student.setLastName("l");
        student.setEmail("e");
        studentDao.create(student);
    }
}
