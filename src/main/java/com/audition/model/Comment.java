package com.audition.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    int postId;
    int id;
    String name;
    String email;
    String body;
}
