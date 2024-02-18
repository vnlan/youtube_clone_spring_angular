package com.personal.youtubeclone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.personal.youtubeclone.dto.CommentDto;
import com.personal.youtubeclone.dto.UploadVideoResponseDto;
import com.personal.youtubeclone.dto.VideoDto;
import com.personal.youtubeclone.service.VideoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor

public class VideoController {

    private final VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponseDto uploadVideo(@RequestParam("file") MultipartFile file){
       return videoService.uploadVideo(file); 
        
    }
    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videoId){
       return videoService.uploadThumbnail(file, videoId); 
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto) {

        videoService.addVideoInfomation(videoDto);
        return videoDto;
    }
    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable String videoId){
        return videoService.getVideoDetails(videoId);
    }
    @PostMapping("/{videoId}/like")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto likeVideo(@PathVariable String videoId){
        return videoService.likeVideo(videoId);
    }
    @PostMapping("/{videoId}/dislike")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto dislikeVideo(@PathVariable String videoId){
        return videoService.dislikeVideo(videoId);
    }
    @PostMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public void addComment(@PathVariable String videoId, @RequestBody CommentDto commentDto){
        videoService.addComment(videoId, commentDto);
    }
    @GetMapping("/{videoId}/all-comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments(@PathVariable String videoId){
        return videoService.getAllComments(videoId);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDto> getAllVideos(){
        return videoService.getAllVideos();
    }


    @PostMapping("/{videoId}/likehihi")
    @ResponseStatus(HttpStatus.OK)
    public String lewlew(@PathVariable String videoId){
        return videoService.testString(videoId);
    }



}