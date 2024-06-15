package com.audition;

import com.audition.integration.AuditionIntegrationClient;
import com.audition.model.AuditionPost;
import com.audition.service.AuditionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AuditionApplicationMockTests {

    @Autowired
    private AuditionService auditionService;

    @MockBean
    private AuditionIntegrationClient auditionIntegrationClient;

    @Autowired
    private MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(AuditionApplicationMockTests.class);

    @Test
    @DisplayName("Get all the available post without filter")
    void getAllTheAvailablePostWithoutFilter() {
        AuditionPost post = new AuditionPost();
        post.setId(1);
        post.setTitle("Test Title");
        post.setBody("Test Body");

        when(auditionIntegrationClient.getPosts()).thenReturn(Collections.singletonList(post));

        List<AuditionPost> posts = auditionService.getPosts();

        assertThat(posts).isNotNull();
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0).getId()).isEqualTo(1);
        assertThat(posts.get(0).getTitle()).isEqualTo("Test Title");
        assertThat(posts.get(0).getBody()).isEqualTo("Test Body");
    }

    @Test
    void getPostByIdTest() {
        AuditionPost post = new AuditionPost();
        post.setId(2);
        post.setTitle("Test Title");
        post.setBody("Test Body");

        when(auditionIntegrationClient.getPostById("1")).thenReturn(post);

        AuditionPost resultPost = auditionService.getPostById("1");

        assertThat(resultPost).isNotNull();
        assertThat(resultPost.getId()).isEqualTo(2);
        assertThat(resultPost.getTitle()).isEqualTo("Test Title");
        assertThat(resultPost.getBody()).isEqualTo("Test Body");
    }

    @Test
    @DisplayName("Get all the available post without filter")
    void restTestCommentWithNoContent() throws Exception {
        when(auditionIntegrationClient.getCommentsForPost("1")).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/{id}/comments", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        logger.info("--------> Test case executed successfully");
    }

}
