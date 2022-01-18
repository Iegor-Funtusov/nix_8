package ua.com.alevel.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.entity.Course;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final SessionFactory sessionFactory;

    public CourseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Course entity) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

//        session.persist(entity);
    }

    @Override
    public void update(Course entity) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Course course = new Course();
            course.setId(id);
            session.delete(course);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean existById(Integer id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session
                .createQuery("select count(course.id) from Course course where course.id = :id")
                .setParameter("id", id);

        boolean existById = (Long) query.getSingleResult() == 1;

        try {
            session.close();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }

        return existById;
    }

    @Override
    public Optional<Course> findById(Integer id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        Optional<Course> optionalCourse = Optional.ofNullable(session.find(Course.class, id));

        try {
            session.close();
        } catch (Exception e) {
            return Optional.empty();
        }

        return optionalCourse;
    }

    @Override
    public List<Course> findAll() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session
                .createQuery("select course from Course course");

        List<Course> courses = (List<Course>) query.getResultList();

        try {
            session.close();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }

        return courses;
    }

    @Override
    public List<Course> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
