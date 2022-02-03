package ua.com.alevel.final_project.persistence.repository.book;

import org.springframework.stereotype.Repository;
import ua.com.alevel.final_project.persistence.entity.books.Book;
import ua.com.alevel.final_project.persistence.entity.books.Publisher;
import ua.com.alevel.final_project.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface BookRepository extends BaseRepository<Book> {

    List<Book> findByPublisher(Publisher publisher);
    List<Book> findByBookNameContaining(String bookName);
    Book findByIsbn(String isbn);
}
