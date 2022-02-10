package ua.com.alevel.final_project.facade;

import java.util.List;

public interface SearchBookFacade {

    List<String> fetchSuggestions(String query);
}
