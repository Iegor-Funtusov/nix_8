package ua.com.alevel.final_project.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ua.com.alevel.final_project.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.final_project.persistence.datatable.DataTableRequest;
import ua.com.alevel.final_project.persistence.datatable.DataTableResponse;
import ua.com.alevel.final_project.persistence.entity.books.Book;
import ua.com.alevel.final_project.persistence.repository.book.BookRepository;
import ua.com.alevel.final_project.service.impl.BookServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceMockTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private CrudRepositoryHelper<Book, BookRepository> crudRepositoryHelper;

    @Mock
    private BookRepository bookRepository;

    private final DataTableResponse<Book> dataTableResponse = new DataTableResponse<>();

    public void setUp() {}

    @Test
    public void shouldDoReturnDataTableResponseOfBook() {
        // given
        DataTableRequest dataTableRequest = new DataTableRequest();
        when(crudRepositoryHelper.findAll(bookRepository, dataTableRequest)).thenReturn(dataTableResponse);

        // when
        DataTableResponse<Book> all = bookService.findAll(dataTableRequest);

        // then
        assertNotNull(all);
        assertThat(all).isInstanceOf(DataTableResponse.class);
    }
}
