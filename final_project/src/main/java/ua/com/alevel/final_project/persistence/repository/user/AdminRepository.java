package ua.com.alevel.final_project.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.final_project.persistence.entity.user.Admin;

@Repository
public interface AdminRepository extends UserRepository<Admin> { }
