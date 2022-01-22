package ua.com.alevel.dao.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        entityManager.createQuery("delete from Student as student " +
                        "where student.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existById(Integer id) {
        Query query = entityManager
                .createQuery("select count(student.id) from Student student " +
                        "where student.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
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
    public List<Student> findAll(DataTableRequest request) {
        int page = (request.getPage() - 1) * request.getSize();
        int size = request.getSize();
        String sort = request.getSort();
        String order = request.getOrder();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Student> root = cq.from(Student.class);
        Join<Student, Course> join = root.join("courses", JoinType.LEFT);
        cq.select(cb.tuple(root, cb.count(join)));
        boolean isNotEmptyMap = MapUtils.isNotEmpty(request.getQueryMap());
        if (isNotEmptyMap) {
            Integer courseId = (Integer) request.getQueryMap().get("courseId");
            cq.where(cb.equal(join.get("id"), courseId));
        }
        cq.groupBy(root.get("id"));
        Expression<?> orderBy = "courses".equals(sort) ? cb.count(join) : root.get(sort);
        if (order.equals("desc")) {
            cq.orderBy(cb.desc(orderBy));
        } else {
            cq.orderBy(cb.asc(orderBy));
        }
        List<Tuple> result = entityManager.createQuery(cq)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
        return result.stream()
                .map(tuple -> (Student) tuple.get(0))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        Query query = entityManager
                .createQuery("select count(student) from Student student");
        return (Long) query.getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countByCourseId(Integer courseId) {
        Query query = entityManager
                .createQuery("select count(student) from Student as student " +
                        "join student.courses as courses " +
                        "where courses.id = :courseId");
        return (Long) query.setParameter("courseId", courseId).getSingleResult();
    }
}
