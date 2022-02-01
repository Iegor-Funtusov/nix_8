package ua.com.alevel.final_project.config.security;

public interface SecurityService {

    boolean isAuthenticated();
    void autoLogin(String username, String password);
    boolean existsByUsername(String username);
}
