package ua.com.alevel.final_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.final_project.elastic.document.BookIndex;
import ua.com.alevel.final_project.persistence.entity.user.Admin;
import ua.com.alevel.final_project.persistence.repository.user.AdminRepository;

import javax.annotation.PreDestroy;

@EnableScheduling
@SpringBootApplication
public class FinalProjectApplication {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder;
    private final ElasticsearchOperations elasticsearchOperations;

    public FinalProjectApplication(
            AdminRepository adminRepository,
            BCryptPasswordEncoder encoder,
            ElasticsearchOperations elasticsearchOperations) {
        this.adminRepository = adminRepository;
        this.encoder = encoder;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @PreDestroy
    public void deleteIndex() {
        elasticsearchOperations.indexOps(BookIndex.class).delete();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createAdmin() {
        if (!adminRepository.existsByUsername("admin@mail.com")) {
            Admin admin = new Admin();
            admin.setUsername("admin@mail.com");
            admin.setPassword(encoder.encode("rootroot"));
            adminRepository.save(admin);
        }
    }
}
