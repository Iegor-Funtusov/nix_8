package ua.com.alevel.final_project.web.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.final_project.persistence.entity.books.Book;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class BookPLPDto {

    private Long id;
    private Boolean exists;
    private String bookName;
    private String imageUrl;
    private String isbn;
    private BigDecimal actualPrice;
    private BigDecimal price;
    private Set<String> authors;
    private Publisher publisher;

    public BookPLPDto(Book book) {
        this.id = book.getId();
        this.exists = book.getExists();
        this.bookName = book.getBookName();
        this.imageUrl = book.getImageUrl();
        this.price = book.getPrice();
        this.isbn = book.getIsbn();
        this.actualPrice = book.getPrice();
        if (CollectionUtils.isNotEmpty(book.getAuthors())) {
            this.authors = book.getAuthors()
                    .stream()
                    .map(author -> author.getFirstName() + " " + author.getLastName())
                    .collect(Collectors.toSet());
        }

        if (book.getPublisher() != null) {
            this.publisher = new Publisher(
                    book.getPublisher().getId(),
                    book.getPublisher().getName());
        }
    }

    private record Publisher(Long id, String name) {

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
