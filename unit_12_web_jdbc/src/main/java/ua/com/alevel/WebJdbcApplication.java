package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ua.com.alevel.store.ConnectionStoreFactory;

@SpringBootApplication
public class WebJdbcApplication {

    private final ConnectionStoreFactory connectionStoreFactory;

    public WebJdbcApplication(ConnectionStoreFactory connectionStoreFactory) {
        this.connectionStoreFactory = connectionStoreFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebJdbcApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initContext() {
        connectionStoreFactory.init();
    }
}
