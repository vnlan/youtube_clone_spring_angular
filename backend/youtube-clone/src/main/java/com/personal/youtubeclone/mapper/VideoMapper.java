package com.personal.youtubeclone.mapper;

import org.springframework.stereotype.Service;

import com.personal.youtubeclone.dto.VideoDto;
import com.personal.youtubeclone.model.Video;

@Service
public class VideoMapper {
    
    public VideoDto mapToDto(Video video) {
        return VideoDto.builder()
                .id(video.getId())
                .videoUrl(video.getVideoUrl())
                .description(video.getDescription())
                .tags(video.getTags())
                .title(video.getTitle())
                .videoStatus(video.getVideoStatus())
                .userId(video.getUserId())
                .thumbnailUrl(video.getThumbnailUrl())
                .likeCount(video.getLikeCount())
                .dislikeCount(video.getDislikeCount())
                .viewCount(video.getViewCount())
                .build();
    }
}
