package com.example.myspringserver.controller;
import com.example.myspringserver.dto.PostScreenDto;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.myspringserver.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/getPost")
    public ResponseEntity<List<PostScreenDto>> getPosts() {
        List<PostScreenDto> posts = postRepository.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertPost(@RequestBody PostScreenDto post) {
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 성공적으로 추가되었습니다.");
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostScreenDto updatedPost) {
        PostScreenDto existingPost = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시물을 찾을 수 없습니다."));

        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());

        postRepository.save(existingPost);
        return ResponseEntity.ok("게시물이 성공적으로 수정되었습니다.");
    }
}
