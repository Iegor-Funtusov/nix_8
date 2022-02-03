package ua.com.alevel.supplier.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.supplier.dto.BookDto;
import ua.com.alevel.supplier.facade.BookFacade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BookFacadeImpl implements BookFacade {

    @Override
    public List<BookDto> findBooksInfoByIsbn(List<String> isbnList) {
        Random random = new Random();
        List<BookDto> bookDtos = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int isbnIndex = random.nextInt(0, 100);
            BookDto bookDto = new BookDto();
            bookDto.setIsbn("isbn" + isbnIndex);
            bookDto.setQuantity(isbnIndex);
            bookDto.setPrice(new BigDecimal("1000.00"));
            if (isbnList.stream().anyMatch(s -> s.equals("isbn" + isbnIndex))) {
                bookDtos.add(bookDto);
            }
        }
        return bookDtos;
    }
}
