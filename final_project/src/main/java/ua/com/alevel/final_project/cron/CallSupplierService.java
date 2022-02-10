package ua.com.alevel.final_project.cron;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.final_project.persistence.entity.books.Book;
import ua.com.alevel.final_project.persistence.repository.book.BookRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallSupplierService {

    @Value("${supplier.url}")
    private String url;

    private final BookRepository bookRepository;

    public CallSupplierService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    @Scheduled(fixedDelay = 60000)
    public void callSupplier() {
        System.out.println("CallSupplierService.callSupplier");

        String token = "978e4a4f-66e6-462c-82c8-44d24a076305";

        List<Book> books = bookRepository.findAll();
        books = books.stream().filter(book -> book.getQuantity() == 0).collect(Collectors.toList());
        List<String> strings = books.stream().map(Book::getIsbn).toList();



        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-auth-token", token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<BookDto[]> response = restTemplate.exchange(
                url + "/api/books?isbnList=isbn1,isbn2,isbn5",
                HttpMethod.GET,
                httpEntity,
                BookDto[].class
        );

        for (BookDto bookDto : response.getBody()) {
            System.out.println("bookDto = " + bookDto);

            Book book = bookRepository.findByIsbn(bookDto.isbn);
            if (book != null) {
                book.setPrice(bookDto.getPrice());
                book.setQuantity(bookDto.getQuantity());
                bookRepository.save(book);
            }
        }
    }

    private static class BookDto {

        private String isbn;
        private Integer quantity;
        private BigDecimal price;

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "BookDto{" +
                    "isbn='" + isbn + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    '}';
        }
    }
}
