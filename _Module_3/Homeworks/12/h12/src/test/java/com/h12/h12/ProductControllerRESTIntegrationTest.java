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
public class ProductControllerRESTIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    void getProductsPageV1() throws Exception {
        this.mockMvc.perform(get("/api/v1/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"name\":\"")))
                .andExpect(content().string(containsString("\"productCategory\":{\"name\":\"")));
    }

    @Test
    void getProductsPageV2() throws Exception {
        this.mockMvc.perform(get("/api/v2/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(containsString("category")));
    }
}
