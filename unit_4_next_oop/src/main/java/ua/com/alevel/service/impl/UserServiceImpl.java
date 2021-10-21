package ua.com.alevel.service.impl;

import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.dao.impl.UserDaoImpl;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.util.Collection;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = ObjectFactory.getInstance().getCurrentObject(UserDao.class);

    @Override
    public void create(User entity) {
        if (!userDao.existByEmail(entity.getEmail())) {
            userDao.create(entity);
        } else {
            System.out.println("user exist by email");
        }
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public Collection<User> findAll() {
        return userDao.findAll();
    }
}
