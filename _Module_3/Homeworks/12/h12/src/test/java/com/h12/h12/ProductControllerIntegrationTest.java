package com.h12.h12;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetProductsPage() throws Exception {
        this.mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<th scope=\"col\">Name</th>")))
                .andExpect(content().string(containsString("<th scope=\"col\">Category</th>")))
                .andExpect(content().string(containsString("<th scope=\"col\">Dishes</th>")))
                .andExpect(content().string(containsString("<a class=\"btn btn-primary mb-5 mt-3\" href=\"/products/add\" role=\"button\">Add new product</a>")));
    }
}
