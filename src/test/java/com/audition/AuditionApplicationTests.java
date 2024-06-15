package com.audition;

import com.audition.integration.AuditionIntegrationClient;
import com.audition.service.AuditionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class AuditionApplicationTests {

    @Autowired
    private AuditionService auditionService;

    @Autowired
    private AuditionIntegrationClient auditionIntegrationClient;

    @Autowired
    private MockMvc mockMvc;

    Logger logger = LoggerFactory.getLogger(AuditionApplicationTests.class);

    @Test
    void contextLoads() {

    }


    @Test
    @DisplayName("Get all the available post without filter")
    void restTestGetAllPostsWithoutFilter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));

        logger.info("--------> Test case executed successfully");
    }

    @Test
    @DisplayName("Get all the available post with userId filter")
    void restTestGetAllPostsWithFilterFilter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/posts?filter={userId}", 3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(3));

        logger.info("--------> Test case executed successfully");
    }

    @Test
    @DisplayName("Get the post by id")
    void restTestGetPostsById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

        logger.info("--------> Test case executed successfully");
    }

    @Test
    @DisplayName("Get all the available comments associated with the post")
    void restTestCommentWithContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/{id}/comments", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        logger.info("--------> Test case executed successfully");
    }

    @Test
    @DisplayName("Lookup for Posts when empty postId is provided")
    void restTestPostWithEmptyId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/{id}", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        logger.info("--------> Test case executed successfully");
    }

}
