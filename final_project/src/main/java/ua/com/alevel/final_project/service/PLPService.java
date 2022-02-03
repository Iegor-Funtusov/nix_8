package ua.com.alevel.final_project.service;

import ua.com.alevel.final_project.persistence.entity.books.Book;

import java.util.List;
import java.util.Map;

public interface PLPService {

    List<Book> search(Map<String, Object> queryMap);
}
