package com.example.project.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private UserDTO creator;
    private List<CommentDTO> comments;
}
