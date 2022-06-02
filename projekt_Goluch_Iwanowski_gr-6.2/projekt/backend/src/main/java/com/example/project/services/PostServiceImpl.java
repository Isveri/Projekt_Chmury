package com.example.project.services;

import com.example.project.domain.Comment;
import com.example.project.domain.Post;
import com.example.project.domain.User;
import com.example.project.exceptions.NotFoundException;
import com.example.project.mappers.CommentMapper;
import com.example.project.mappers.PostMapper;
import com.example.project.model.CommentDTO;
import com.example.project.model.PostDTO;
import com.example.project.repositories.CommentRepository;
import com.example.project.repositories.PostRepository;
import com.example.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;


    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(groupRoom -> postMapper.mapPostToPostDTO(groupRoom))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostByName(String name) {
        return postMapper.mapPostToPostDTO(postRepository.findByTitle(name).orElseThrow(() -> new NotFoundException("Post not found")));
    }

    @Override
    public PostDTO save(PostDTO postDTO) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = currentUser.getId();
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found id:"+id));
        Post post = postMapper.mapPostDTOToPost(postDTO);
        user.getPosts().add(post);
        post.setCreator(user);
        return postMapper.mapPostToPostDTO(postRepository.save(post));
    }

    @Override
    public PostDTO getPostById(Long id) {
        return postRepository.findById(id)
                .map(postMapper::mapPostToPostDTO)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }

    @Override
    public PostDTO saveAndReturnDTO(Post post) {
        return postMapper.mapPostToPostDTO(postRepository.save(post));
    }

    @Override
    public PostDTO updatePostByDTO(Long id, PostDTO postDTO) {
        postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found id:"+id));
        Post post = postMapper.mapPostDTOToPost(postDTO);
        post.setId(id);
        return saveAndReturnDTO(post);
    }

    @Override
    public void addComment(CommentDTO commentDTO) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = currentUser.getId();
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found id:"+id));

        Comment comment = commentMapper.mapCommentDTOTOComment(commentDTO);

        comment.setUser(user);
        comment.setPost(postRepository.findById(commentDTO.getPostId()).orElseThrow(() -> new NotFoundException("User not found id:"+id)));

        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }


    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
