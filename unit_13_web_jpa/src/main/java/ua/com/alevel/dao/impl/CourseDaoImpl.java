package ua.com.alevel.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.entity.Course;

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

    }

    @Override
    public boolean existById(Integer id) {
        return false;
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
        return null;
    }
}
