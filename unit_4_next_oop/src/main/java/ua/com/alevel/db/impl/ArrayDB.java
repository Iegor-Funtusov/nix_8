package ua.com.alevel.db.impl;

import ua.com.alevel.db.UserDB;
import ua.com.alevel.entity.User;

import java.util.Collection;

public class ArrayDB implements UserDB {

    private final User[] users = new User[10];

    @Override
    public void create(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public boolean existByEmail(String email) {
        return false;
    }
}
