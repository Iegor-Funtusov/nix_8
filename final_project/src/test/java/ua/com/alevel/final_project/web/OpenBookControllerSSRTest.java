package ua.com.alevel.final_project.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.alevel.final_project.facade.PLPFacade;
import ua.com.alevel.final_project.web.dto.response.BookPLPDto;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpenBookControllerSSRTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Mock
    private PLPFacade plpFacade;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
//                .apply(springSecurity())
                .build();
    }

    @Test
    public void shouldDoRedirectWhenParamIsValid() throws Exception {
        MockHttpServletRequestBuilder createMessage = post("/books/search")
                .param("bookSearch", "test");
        this.mockMvc.
                perform(createMessage)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books?bookSearch=test"));
    }

    @Test
    public void shouldDoRedirectWhenParamIsNotValid() throws Exception {
        MockHttpServletRequestBuilder createMessage = post("/books/search");
        this.mockMvc.
                perform(createMessage)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldDoReturnViewPageWhenParamIsValid() throws Exception {
        MockHttpServletRequestBuilder createMessage = get("/books");
        this.mockMvc.
                perform(createMessage)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/pages/open/plp"));
    }

    @Test
    public void shouldDoReturnViewPageWhenParamIsNotValid() throws Exception {
        MockHttpServletRequestBuilder createMessage = get("/books");
        this.mockMvc.
                perform(createMessage)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/pages/open/plp"))
                .andExpect(model().attribute("bookList", new ArrayList<BookPLPDto>()))
        ;
    }
}
