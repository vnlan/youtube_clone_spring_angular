package com.personal.youtubeclone.dto;


import java.util.Set;

import org.springframework.data.annotation.Id;

import com.personal.youtubeclone.model.VideoStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDto {
    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
    private Integer viewCount;
    private Integer likeCount;
    private Integer dislikeCount;
}
