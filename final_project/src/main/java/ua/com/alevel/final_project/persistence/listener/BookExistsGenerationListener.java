package ua.com.alevel.final_project.persistence.listener;

import ua.com.alevel.final_project.persistence.entity.books.Book;

import javax.persistence.PostLoad;

public class BookExistsGenerationListener {

    @PostLoad
    public void generateBookExists(Book book) {
        book.setExists(book.getQuantity() != 0);
    }
}
