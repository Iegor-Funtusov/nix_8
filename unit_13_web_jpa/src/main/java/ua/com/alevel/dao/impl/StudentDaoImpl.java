package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;

import javax.persistence.EntityManager;
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
    public void update(Student entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public boolean existById(Integer id) {
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}
