package com.dbarvekar.blog.impl;

import com.dbarvekar.blog.entities.Comment;
import com.dbarvekar.blog.entities.Post;
import com.dbarvekar.blog.exceptions.ResourceNotFoundException;
import com.dbarvekar.blog.payloads.CommentDTO;
import com.dbarvekar.blog.repositories.CommentRepository;
import com.dbarvekar.blog.repositories.PostRepository;
import com.dbarvekar.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDTO create(CommentDTO commentDTO, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id %d".formatted(postId)));

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        comment = commentRepository.save(comment);

        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id %d".formatted(commentId)));
        commentRepository.deleteById(commentId);
    }
}
