package com.personal.youtubeclone.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.youtubeclone.dto.CommentDto;
import com.personal.youtubeclone.dto.UploadVideoResponseDto;
import com.personal.youtubeclone.dto.VideoDto;
import com.personal.youtubeclone.mapper.CommentMapper;
import com.personal.youtubeclone.mapper.VideoMapper;
import com.personal.youtubeclone.model.Comment;
import com.personal.youtubeclone.model.Video;
import com.personal.youtubeclone.model.VideoStatus;
import com.personal.youtubeclone.repository.VideoRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor

public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;
    private final UserService userService;
    private final VideoMapper videoMapper;
    private final CommentMapper commentMapper;

    public UploadVideoResponseDto uploadVideo(MultipartFile file){
        String videoUrl = s3Service.uploadFile(file);
        Video video = new Video();
        video.setVideoUrl(videoUrl);

        Video savedVideo = videoRepository.save(video);
        return new UploadVideoResponseDto(savedVideo.getId(),savedVideo.getVideoUrl());
    }

    public VideoDto addVideoInfomation(VideoDto videoDto) {
        Video savedVideo = videoRepository.findById(videoDto.getId()).get();
        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setTags(videoDto.getTags());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());

        videoRepository.save(savedVideo);
        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        Video savedVideo = videoRepository.findById(videoId).get();
        String thumbnailUrl = s3Service.uploadFile(file);
        savedVideo.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(savedVideo);
        return thumbnailUrl;
    }
    public void increaseViewCount(Video video) {
        video.increaseViewCount();
        videoRepository.save(video);
    }

    public VideoDto getVideoDetails(String videoId) {
        Video savedVideo = videoRepository.findById(videoId).get();

        increaseViewCount(savedVideo);
        userService.addVideoToHistory(videoId);

        return videoMapper.mapToDto(savedVideo);
    }



    public VideoDto likeVideo(String videoId) {
        Video likedVideo = videoRepository.findById(videoId).get();

        if(userService.ifLikedVideo(videoId)){
            likedVideo.decreaseLikes();
            userService.removeFromLikedVideos(videoId);
        }
        else if(userService.ifDislikedVideo(videoId)){
            likedVideo.decreaseDislikes();
            userService.removeFromDislikedVideos(videoId);
            likedVideo.increaseLikes();
            userService.addToLikedVideos(videoId);
        }
        else{
            likedVideo.increaseLikes();
            userService.addToLikedVideos(videoId);
        }

        videoRepository.save(likedVideo);


        return videoMapper.mapToDto(likedVideo);
    }
    public String testString(String videoId) {
        return userService.testString(videoId);
    }

    public VideoDto dislikeVideo(String videoId) {

        Video dislikedVideo = videoRepository.findById(videoId).get();

        if(userService.ifDislikedVideo(videoId)){
            dislikedVideo.decreaseDislikes();
            userService.removeFromDislikedVideos(videoId);
        }
        else if(userService.ifLikedVideo(videoId)){
            dislikedVideo.decreaseLikes();
            userService.removeFromLikedVideos(videoId);
            dislikedVideo.increaseDislikes();
            userService.addToDislikedVideos(videoId);
        }
        else{
            dislikedVideo.increaseDislikes();
            userService.addToDislikedVideos(videoId);
        }

        videoRepository.save(dislikedVideo);


        return videoMapper.mapToDto(dislikedVideo);
       
    }

    public void addComment(String videoId, CommentDto commentDto) {
       
        Video video = videoRepository.findById(videoId).get();
        Comment comment = commentMapper.mapToEntity(commentDto);
        video.addComment(comment);

        videoRepository.save(video);
    }

    public List<CommentDto> getAllComments(String videoId) {
        Video video = videoRepository.findById(videoId).get();
        List<Comment> commentList = video.getCommentList();
    
       return commentList.stream().map(commentMapper::mapToDto).collect(Collectors.toList());
    }  

    public List<VideoDto> getAllVideos() {
        return videoRepository.findAll()
                .stream()
                .filter(video -> VideoStatus.PUBLIC.equals(video.getVideoStatus()))
                .map(videoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
