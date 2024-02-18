package com.personal.youtubeclone.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "video")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Video {
    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private AtomicInteger likeCount = new AtomicInteger(0);
    private AtomicInteger dislikeCount = new AtomicInteger(0);
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private AtomicInteger viewCount = new AtomicInteger(0);
    private String thumbnailUrl;
    private List<Comment> commentList = new ArrayList<>();

    public Integer getLikeCount() {
        return this.likeCount.get();
    }

    public Integer getDislikeCount() {
        return this.dislikeCount.get();
    }
    
    public Integer getViewCount() {
        return this.viewCount.get();
    }

    public void increaseLikes(){
        this.likeCount.incrementAndGet(); 
    }
    public void decreaseLikes(){
        this.likeCount.decrementAndGet(); 
    }
        public void increaseDislikes(){
        this.dislikeCount.incrementAndGet(); 
    }
    public void decreaseDislikes(){
        this.dislikeCount.decrementAndGet();
    }

    public void increaseViewCount() {
        this.viewCount.incrementAndGet(); 
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

}
