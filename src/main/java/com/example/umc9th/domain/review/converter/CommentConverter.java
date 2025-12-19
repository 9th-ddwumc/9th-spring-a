package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.CommentResDTO;
import com.example.umc9th.domain.review.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentConverter {

    public CommentResDTO.CommentDTO toCommentDTO(Comment comment) {
        return CommentResDTO.CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public List<CommentResDTO.CommentDTO> toCommentDTOList(List<Comment> comments) {
        return comments.stream()
                .map(this::toCommentDTO)
                .toList();
    }
}
