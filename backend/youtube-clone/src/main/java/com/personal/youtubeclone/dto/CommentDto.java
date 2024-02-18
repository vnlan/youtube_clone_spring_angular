package com.personal.youtubeclone.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    
    private String id;
    private String content;
    private String authorId;
    private Integer likeCount;
    private Integer dislikeCount;


}
