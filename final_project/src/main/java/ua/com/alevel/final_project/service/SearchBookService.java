package ua.com.alevel.final_project.service;

import java.util.List;

public interface SearchBookService {

    List<String> fetchSuggestions(String query);
}
