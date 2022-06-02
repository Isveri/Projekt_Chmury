package com.example.project.services;

import com.example.project.domain.Post;
import com.example.project.model.CommentDTO;
import com.example.project.model.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO getPostByName(String name);

    PostDTO save(PostDTO postDTO);

    PostDTO getPostById(Long id);

    PostDTO saveAndReturnDTO(Post post);

    PostDTO updatePostByDTO(Long id, PostDTO postDTO);

    void addComment(CommentDTO commentDTO);

    void deleteCommentById(Long commentId);

    void deletePostById(Long id);
}

