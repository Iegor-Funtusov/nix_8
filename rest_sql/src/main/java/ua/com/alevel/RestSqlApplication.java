package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RestSqlApplication {

    private final JdbcConfig jdbcConfig;

    public RestSqlApplication(JdbcConfig jdbcConfig) {
        this.jdbcConfig = jdbcConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestSqlApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        jdbcConfig.init();
    }
}
