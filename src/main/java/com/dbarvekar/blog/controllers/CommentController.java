package com.dbarvekar.blog.controllers;

import com.dbarvekar.blog.payloads.ApiResponse;
import com.dbarvekar.blog.payloads.CommentDTO;
import com.dbarvekar.blog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable("postId") Long postId){
        CommentDTO commentDTO1 = commentService.create(commentDTO, postId);
        return new ResponseEntity<>(commentDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Long commentId){
        commentService.delete(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Successfully deleted", true), HttpStatus.OK);
    }
}
