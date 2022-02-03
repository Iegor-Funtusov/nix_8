package ua.com.alevel.final_project.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.final_project.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.final_project.persistence.entity.books.Book;
import ua.com.alevel.final_project.persistence.entity.books.Publisher;
import ua.com.alevel.final_project.persistence.repository.book.BookRepository;
import ua.com.alevel.final_project.persistence.repository.book.PublisherRepository;
import ua.com.alevel.final_project.service.PLPService;
import ua.com.alevel.final_project.util.WebUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PLPServiceImpl implements PLPService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final CrudRepositoryHelper<Publisher, PublisherRepository> crudRepositoryHelper;

    public PLPServiceImpl(
            BookRepository bookRepository,
            PublisherRepository publisherRepository,
            CrudRepositoryHelper<Publisher, PublisherRepository> crudRepositoryHelper) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public List<Book> search(Map<String, Object> queryMap) {
        if (queryMap.get(WebUtil.PUBLISHER_PARAM) != null) {
            Long publisherId = Long.parseLong(String.valueOf(queryMap.get(WebUtil.PUBLISHER_PARAM)));
            Optional<Publisher> publisher = crudRepositoryHelper.findById(publisherRepository, publisherId);
            return bookRepository.findByPublisher(publisher.get());
        }
        if (queryMap.get(WebUtil.SEARCH_BOOK_PARAM) != null) {
            String searchBook = (String) queryMap.get(WebUtil.SEARCH_BOOK_PARAM);
            return bookRepository.findByBookNameContaining(searchBook);
        }
        return bookRepository.findAll();
    }
}
