package ua.com.alevel.final_project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.final_project.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.final_project.persistence.datatable.DataTableRequest;
import ua.com.alevel.final_project.persistence.datatable.DataTableResponse;
import ua.com.alevel.final_project.persistence.entity.books.Book;
import ua.com.alevel.final_project.persistence.repository.book.BookRepository;
import ua.com.alevel.final_project.service.BookService;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CrudRepositoryHelper<Book, BookRepository> crudRepositoryHelper;

    public BookServiceImpl(
            BookRepository bookRepository,
            CrudRepositoryHelper<Book, BookRepository> crudRepositoryHelper) {
        this.bookRepository = bookRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Book create(Book entity) {
        crudRepositoryHelper.create(bookRepository, entity);
        return entity;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return crudRepositoryHelper.findById(bookRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Book> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(bookRepository, request);
    }
}
