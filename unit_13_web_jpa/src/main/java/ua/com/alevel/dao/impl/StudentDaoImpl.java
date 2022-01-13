package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {

    private final EntityManager entityManager;

    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void create(Student entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void update(Student entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void delete(Integer id) {
        entityManager.remove(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existById(Integer id) {
        Query query = entityManager
                .createQuery("select count(student.id) from Student student where student.id = :id")
                .setParameter("id", id);
        return (Integer) query.getSingleResult() == 1;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return (List<Student>) entityManager
                .createQuery("select student from Student student")
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findByCourseId(Integer courseId) {
        List<Integer> ids = List.of(courseId);
        return (List<Student>) entityManager
                .createQuery("select student from Student as student join student.courses as courses where courses.id in :ids")
                .setParameter("ids", ids)
                .getResultList();
    }
}
