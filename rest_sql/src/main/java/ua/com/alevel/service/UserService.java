package ua.com.alevel.service;

import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.User;

public interface UserService {

    void create(User user);
    void update(User user);
    void delete(Integer id);
    User findById(Integer id);
    User findByEmail(String email);
    DataTableResponse<User> findAll(WebRequest request);
}
