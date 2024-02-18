package com.personal.youtubeclone.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.personal.youtubeclone.dto.CommentDto;
import com.personal.youtubeclone.model.Comment;

import org.springframework.stereotype.Service;

@Service
public class CommentMapper {
    
    public Comment mapToEntity(CommentDto commentDto) {
        return Comment.builder()
                .authorId(commentDto.getAuthorId())
                .content(commentDto.getContent())
                .build();
    }

    public CommentDto mapToDto(Comment comment) {
        return CommentDto.builder()
                .content(comment.getContent())
                .authorId(comment.getAuthorId())
                .likeCount(comment.getLikeCount())
                .dislikeCount(comment.getDislikeCount())
                .build();
    }

    public List<CommentDto> mapToDtoList(List<Comment> comments) {
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}
