package ua.com.alevel.final_project.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.alevel.final_project.persistence.datatable.DataTableRequest;
import ua.com.alevel.final_project.persistence.datatable.DataTableResponse;
import ua.com.alevel.final_project.persistence.entity.books.Book;
import ua.com.alevel.final_project.util.WebUtil;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceDBTest {

    @Autowired
    private BookService bookService;

    private final DataTableRequest dataTableRequest = WebUtil.generateDefaultDataTableRequest();
    private final Book book = new Book();

    @Before
    public void setUp() {
        book.setBookName("BookName");
        book.setIsbn("isbn");
    }

    @Test
    @Order(1)
    public void shouldDoCreateBook() {
        Book createdBook = bookService.create(book);

        assertNotNull(createdBook);
    }

    @Test
    @Order(2)
    public void shouldDoCreateBookWhereIsbnIsNull() {
        // given
        book.setIsbn(null);

        // when
       Exception thrown = Assertions.assertThrows(Exception.class, () -> bookService.create(book));

       // then
       assertThat(thrown).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @Order(3)
    public void shouldDoReturnDataTableResponseOfBook() {
        DataTableResponse<Book> dataTableResponse = bookService.findAll(dataTableRequest);

        assertNotNull(dataTableResponse);
        assertEquals(8, dataTableResponse.getItemsSize());
    }

    @Test
    @Order(4)
    public void shouldDoReturnOptionalBookById() {
        Optional<Book> optionalBook = bookService.findById(1L);

        assertTrue(optionalBook.isPresent());
    }

    @Test
    @Order(5)
    public void shouldDoThrowExceptionWhenIdIsNotPresent() {
        // given
        final Long id = 10L;

        // when
        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> bookService.findById(id));

        // then
        assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
        assertThat(thrown.getMessage()).isEqualTo("this entity is not found");
    }
}
