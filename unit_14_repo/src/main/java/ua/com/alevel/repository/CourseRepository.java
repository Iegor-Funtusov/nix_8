package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.Course;

@Repository
public interface CourseRepository extends BaseRepository<Course> {

    @Query("select count(c) from Course c join c.students as s where s.id = :studentId")
    long countByStudentId(@Param("studentId") Integer id);
}
