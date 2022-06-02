package com.example.project.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PostListDTO {
    private List<PostDTO> posts;
}
