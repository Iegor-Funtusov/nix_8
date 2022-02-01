package ua.com.alevel.final_project.service;

import ua.com.alevel.final_project.persistence.entity.BaseEntity;

public interface CrudService<E extends BaseEntity> {

    E create(E entity);
    E update(E entity);
    void delete(Long id);
}
