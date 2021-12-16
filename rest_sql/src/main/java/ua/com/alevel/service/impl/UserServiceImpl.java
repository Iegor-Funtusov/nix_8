package ua.com.alevel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.User;
import ua.com.alevel.exception.NoContentException;
import ua.com.alevel.exception.UnprocessableEntityException;
import ua.com.alevel.service.UserService;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        if (userDao.existByEmail(user.getEmail())) {
            throw new UnprocessableEntityException("Email is invalid or already taken");
        }
        userDao.create(user);
    }

    @Override
    public void update(User user) {
        checkExistById(user.getId());
        userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        checkExistById(id);
        userDao.delete(id);
    }

    @Override
    public User findById(Integer id) {
        checkExistById(id);
        return userDao.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        checkExistByEmail(email);
        return userDao.findByEmail(email);
    }

    //localhost:8080/api/users?page=1&size=10&search=id,name,email

    @Override
    public DataTableResponse<User> findAll(WebRequest webRequest) {
        DataTableRequest request = new DataTableRequest();
//        int page = Integer.parseInt(webRequest.getParameterMap().get("page")[0]);
//        int size = Integer.parseInt(webRequest.getParameterMap().get("size")[0]);
//        String sort = webRequest.getParameterMap().get("sort")[0];
//        String order = webRequest.getParameterMap().get("order")[0];
//
//        request.setSize(size);
//        request.setPage(page);
//        request.setOrder(order);
//        request.setSort(sort);

        return userDao.findAll(request);
    }

    private void checkExistById(Integer id) {
        if (!userDao.existById(id)) {
            throw new NoContentException("no content by this resource");
        }
    }

    private void checkExistByEmail(String email) {
        if (!userDao.existByEmail(email)) {
            throw new NoContentException("no content by this resource");
        }
    }
}
