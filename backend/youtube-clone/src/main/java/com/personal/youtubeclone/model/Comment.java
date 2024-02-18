package com.personal.youtubeclone.model;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(value = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    private String id;
    private String content;
    private String authorId;
    private AtomicInteger likeCount = new AtomicInteger(0);
    private AtomicInteger dislikeCount = new AtomicInteger(0);

    public Integer getLikeCount() {
        return this.likeCount.get();
    }

    public Integer getDislikeCount() {
        return this.dislikeCount.get();
    }
}
