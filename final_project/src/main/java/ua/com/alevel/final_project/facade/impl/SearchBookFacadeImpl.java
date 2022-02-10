package ua.com.alevel.final_project.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.final_project.facade.SearchBookFacade;
import ua.com.alevel.final_project.service.SearchBookService;

import java.util.List;

@Service
public class SearchBookFacadeImpl implements SearchBookFacade {

    private final SearchBookService searchBookService;

    public SearchBookFacadeImpl(SearchBookService searchBookService) {
        this.searchBookService = searchBookService;
    }

    @Override
    public List<String> fetchSuggestions(String query) {
        return searchBookService.fetchSuggestions(query);
    }
}
