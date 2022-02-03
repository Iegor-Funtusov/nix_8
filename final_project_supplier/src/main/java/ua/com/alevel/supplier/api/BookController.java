package ua.com.alevel.supplier.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.supplier.dto.BookDto;
import ua.com.alevel.supplier.facade.BookFacade;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookFacade bookFacade;

    public BookController(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> find(@RequestParam String isbnList) {
        List<String> isbns = Arrays.asList(isbnList.split(","));
        return ResponseEntity.ok(bookFacade.findBooksInfoByIsbn(isbns));
    }
}
