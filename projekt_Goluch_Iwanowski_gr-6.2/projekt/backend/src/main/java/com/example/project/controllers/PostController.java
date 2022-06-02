package com.example.project.controllers;

import com.example.project.model.CommentDTO;
import com.example.project.model.PostDTO;
import com.example.project.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<PostDTO> getUserByName(@RequestParam String name) {
        return ResponseEntity.ok(postService.getPostByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.save(postDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.updatePostByDTO(id, postDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/newComment")
    public ResponseEntity<Void> addComment(@RequestBody CommentDTO commentDTO) {
        postService.addComment(commentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long commentId){
        postService.deleteCommentById(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
