package ua.com.alevel.final_project.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.final_project.persistence.entity.user.User;
import ua.com.alevel.final_project.persistence.repository.BaseRepository;

import java.util.Optional;

@Repository
public interface UserRepository<U extends User> extends BaseRepository<U> {

    boolean existsByUsername(String username);
    Optional<U> findByUsername(String username);
}
