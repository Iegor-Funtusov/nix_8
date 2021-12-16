package ua.com.alevel.dao;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.User;

public interface UserDao {

    void create(User user);
    void update(User user);
    void delete(Integer id);
    boolean existById(Integer id);
    boolean existByEmail(String email);
    User findById(Integer id);
    User findByEmail(String email);
    DataTableResponse<User> findAll(DataTableRequest request);
}
