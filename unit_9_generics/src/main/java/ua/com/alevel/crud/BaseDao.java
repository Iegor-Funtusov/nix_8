package ua.com.alevel.crud;

import java.util.List;

public interface BaseDao<E extends BaseEntity> {

    void create(E e);
    void update(E e);
    E findById(String id);
    List<E> findAll();
}
