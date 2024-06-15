package com.audition.web;

import com.audition.common.exception.SystemException;
import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import com.audition.service.AuditionService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditionController {

    @Autowired
    AuditionService auditionService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AuditionPost> getPosts(@RequestParam(value = "filter", required = false) Integer userId) {
        List<AuditionPost> posts = auditionService.getPosts();
        if (userId == null) {
            //if User did not provide any filter, return all posts
           return posts;
        }
        return posts.stream().filter(i -> i.getUserId() == userId).collect(Collectors.toList());
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AuditionPost getPosts(@PathVariable("id") final String postId) {
        if (postId == null || postId.isEmpty()) {
            throw new SystemException("Post ID is either empty or invalid", HttpStatus.BAD_REQUEST.value());
        }
        return auditionService.getPostById(postId);
    }

    @RequestMapping(value = "/posts/{id}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Comment> getCommentsForPost(@PathVariable("id") final String postId) {
        List<Comment> comments = auditionService.getCommentsForPost(postId);
        if(comments == null || comments.isEmpty()) {
            //-> Throw a new exception with 204 No-content status
            throw new SystemException("No comments found for given post id: " + postId, 204);
        }

        return comments;
    }

}
