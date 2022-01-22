package ua.com.alevel.dao.impl;

import org.apache.commons.collections4.MapUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.stereotype.Repository;

import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;

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
public class CourseDaoImpl implements CourseDao {

    private final SessionFactory sessionFactory;

    public CourseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Course entity) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession(session);
        }
    }

    @Override
    public void update(Course entity) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession(session);
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createQuery("delete from Course as course " +
                            "where course.id = :id").
                    setParameter("id", id).
                    executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession(session);
        }
    }

    @Override
    public boolean existById(Integer id) {
        Session session = getSession();
        Query query = session
                .createQuery("select count(course.id) from Course as course " +
                        "where course.id = :id")
                .setParameter("id", id);
        boolean existById = (Long) query.getSingleResult() == 1;
        closeSession(session);
        return existById;
    }

    @Override
    public Optional<Course> findById(Integer id) {
        Session session = getSession();
        Optional<Course> optionalCourse = Optional.ofNullable(session.find(Course.class, id));
        closeSession(session);
        return optionalCourse;
    }

    @Override
    public List<Course> findAll() {
        Session session = getSession();
        Query query = session
                .createQuery("select course from Course as course");
        List<Course> courses = (List<Course>) query.getResultList();
        closeSession(session);
        return courses;
    }

    @Override
    public List<Course> findAll(DataTableRequest request) {
        int page = (request.getPage() - 1) * request.getSize();
        int size = request.getSize();
        String sort = request.getSort();
        String order = request.getOrder();

        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Course> root = cq.from(Course.class);
        Join<Course, Student> join = root.join("students", JoinType.LEFT);
        cq.select(cb.tuple(root, cb.count(join)));
        boolean isNotEmptyMap = MapUtils.isNotEmpty(request.getQueryMap());
        if (isNotEmptyMap) {
            Integer studentId = (Integer) request.getQueryMap().get("studentId");
            cq.where(cb.equal(join.get("id"), studentId));
        }
        cq.groupBy(root.get("id"));
        Expression<?> orderBy = "students".equals(sort) ? cb.count(join) : root.get(sort);
        if (order.equals("desc")) {
            cq.orderBy(cb.desc(orderBy));
        } else {
            cq.orderBy(cb.asc(orderBy));
        }
        List<Tuple> result = session.createQuery(cq)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
        closeSession(session);
        return result.stream()
                .map(tuple -> (Course) tuple.get(0))
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        Session session = getSession();
        Query query = getSession()
                .createQuery("select count(course) from Course as course");
        long count = (Long) query.getSingleResult();
        closeSession(session);
        return count;
    }

    @Override
    public long countByStudentId(Integer studentId) {
        Session session = getSession();
        Query query = session
                .createQuery("select count(course) from Course as course " +
                        "join course.students as students " +
                        "where students.id = :studentId");
        long count = (Long) query.setParameter("studentId", studentId).getSingleResult();
        closeSession(session);
        return count;
    }

    private Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    private void closeSession(Session session) {
        try {
            session.close();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
