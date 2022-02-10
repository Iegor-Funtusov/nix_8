package ua.com.alevel.final_project.cron;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.com.alevel.final_project.elastic.document.BookIndex;
import ua.com.alevel.final_project.elastic.repository.BookIndexRepository;
import ua.com.alevel.final_project.persistence.entity.books.Book;
import ua.com.alevel.final_project.persistence.repository.book.BookRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SyncElasticCronService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final BookIndexRepository bookIndexRepository;
    private final BookRepository bookRepository;

    public SyncElasticCronService(
            ElasticsearchOperations elasticsearchOperations,
            BookIndexRepository bookIndexRepository,
            BookRepository bookRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.bookIndexRepository = bookIndexRepository;
        this.bookRepository = bookRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void syncToSupplier() {
        elasticsearchOperations.indexOps(BookIndex.class).refresh();
        bookIndexRepository.deleteAll();
        bookIndexRepository.saveAll(prepareDataset());
    }

    private Collection<BookIndex> prepareDataset() {
        List<Book> books = bookRepository.findAll();
        List<BookIndex> bookIndices = new ArrayList<>();
        books.forEach(book -> {
            BookIndex bookIndex = new BookIndex();
            bookIndex.setBookName(book.getBookName());
            bookIndices.add(bookIndex);
        });
        return bookIndices;
    }
}
