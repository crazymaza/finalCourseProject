package com.github.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ClientApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "User1", password = "123")
    public void checkClientCardsFromOtherModule() throws Exception {
        mockMvc
                .perform(post("http://localhost:8080/atm/show"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
