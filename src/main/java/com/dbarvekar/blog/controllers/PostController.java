package com.dbarvekar.blog.controllers;

import com.dbarvekar.blog.payloads.ApiResponse;
import com.dbarvekar.blog.payloads.PostDTO;
import com.dbarvekar.blog.payloads.PostResponse;
import com.dbarvekar.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO postDTO, @PathVariable Long userId, @PathVariable Long categoryId){
        PostDTO savedPostDTO = postService.create(postDTO, userId, categoryId);
        return new ResponseEntity<PostDTO>(savedPostDTO,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<PostDTO> update(@RequestBody PostDTO postDTO){
        PostDTO updated = postService.update(postDTO);
        return new ResponseEntity<PostDTO>(updated,HttpStatus.ACCEPTED);
    }

    //GET all post
    @GetMapping("/get-all-post")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
                                                   @RequestParam(value = "pageNumber", defaultValue = "10", required = false) Integer pageNumber,
                                                   @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        PostResponse allPost = postService.getAllPost(pageSize, pageNumber, sortBy, sortDir);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    //GET post by id
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable("id") Long id){
        PostDTO postById = postService.getPostById(id);
        return new ResponseEntity<>(postById, HttpStatus.OK);
    }

    //DELETE post
    @DeleteMapping("/delete/{id}")
    public ApiResponse deletePost(@PathVariable("id") Long id){
        postService.delete(id);
        return new ApiResponse("Successfully deleted", true);
    }

    //GET post by user id
    @GetMapping("/get-by-user-id/{userId}")
    public ResponseEntity<List<PostDTO>> getByUserId(@PathVariable("userId") Long userId){
        List<PostDTO> postByUser = postService.getPostByUser(userId);
        return new ResponseEntity<>(postByUser, HttpStatus.OK);
    }

    //GET post by category id
    @GetMapping("/get-by-category-id/{categoryId}")
    public ResponseEntity<List<PostDTO>> getByCategoryId(@PathVariable("categoryId") Long categoryId){
        List<PostDTO> postByCategory = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postByCategory, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keyword") String keyword){
        List<PostDTO> postDTOS = postService.searchPost(keyword);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }
}
