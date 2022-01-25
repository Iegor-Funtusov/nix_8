package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.alevel.entity.Student;

@Repository
public interface StudentRepository extends BaseRepository<Student> {

    @Query("select count(s) from Student s join s.courses as c where c.id =: courseId")
    long countByCourses(@Param("courseId") Integer courseId);

    boolean existsById(Integer id);
}
