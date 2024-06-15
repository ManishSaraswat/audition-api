package com.audition.integration;

import com.audition.common.exception.SystemException;
import com.audition.model.AuditionPost;
import java.util.ArrayList;
import java.util.List;

import com.audition.model.Comment;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuditionIntegrationClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public List<AuditionPost> getPosts() {
        String url = BASE_URL +"/posts";
        ResponseEntity<List<AuditionPost>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public AuditionPost getPostById(final String id) {

        try {
            String url = BASE_URL + "/posts/" + id;
            ResponseEntity<AuditionPost> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
            );
            return response.getBody();
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new SystemException("Cannot find a Post with id " + id, "Resource Not Found",
                    404);
            } else {
                throw new SystemException("Unknown Error message", e);
            }
        }
    }

    public List<Comment> getCommentsForPost(final String postId) {
        String url = BASE_URL + "/posts/" + postId + "/comments";
        ResponseEntity<List<Comment>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }
}
