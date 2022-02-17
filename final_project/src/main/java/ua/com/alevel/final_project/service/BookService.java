package ua.com.alevel.final_project.service;

import ua.com.alevel.final_project.persistence.datatable.DataTableRequest;
import ua.com.alevel.final_project.persistence.datatable.DataTableResponse;
import ua.com.alevel.final_project.persistence.entity.books.Book;

import java.util.Optional;

public interface BookService extends CrudService<Book> {

    Optional<Book> findById(Long id);

    DataTableResponse<Book> findAll(DataTableRequest request);
}
