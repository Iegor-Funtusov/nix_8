package ua.com.alevel.final_project.persistence.repository.book;

import org.springframework.stereotype.Repository;
import ua.com.alevel.final_project.persistence.entity.books.Author;
import ua.com.alevel.final_project.persistence.repository.BaseRepository;

@Repository
public interface AuthorRepository extends BaseRepository<Author> { }
