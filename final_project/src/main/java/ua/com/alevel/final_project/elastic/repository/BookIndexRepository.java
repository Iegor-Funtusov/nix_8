package ua.com.alevel.final_project.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ua.com.alevel.final_project.elastic.document.BookIndex;

@Repository
public interface BookIndexRepository extends ElasticsearchRepository<BookIndex, String> { }
