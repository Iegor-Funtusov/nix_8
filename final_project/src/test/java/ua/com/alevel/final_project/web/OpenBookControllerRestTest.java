package ua.com.alevel.final_project.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpenBookControllerRestTest {

    @LocalServerPort
    String port;

    @Test
    public void testGetReturnExpectedBooksWhenRequestParamIsNotPresent() {
        when().
                get("http://localhost:" + port + "/books/suggestions").
                then()
                .statusCode(400);
    }

    @Test
    public void testGetReturnExpectedBooksWhenRequestParamIsPresent() {
        when().
                get("http://localhost:" + port + "/books/suggestions?query=q").
                then()
                .statusCode(200);
    }
}
