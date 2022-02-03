package ua.com.alevel.supplier.facade;

import ua.com.alevel.supplier.dto.BookDto;

import java.util.List;

public interface BookFacade {

    List<BookDto> findBooksInfoByIsbn(List<String> isbnList);
}
