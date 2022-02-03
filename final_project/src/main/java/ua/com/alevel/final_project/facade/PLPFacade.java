package ua.com.alevel.final_project.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.final_project.web.dto.response.BookPLPDto;

import java.util.List;

public interface PLPFacade {

    List<BookPLPDto> search(WebRequest webRequest);
    List<String> searchBookName(String query);
}
