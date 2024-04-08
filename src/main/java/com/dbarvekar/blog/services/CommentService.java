package com.dbarvekar.blog.services;

import com.dbarvekar.blog.payloads.CommentDTO;

public interface CommentService {

    CommentDTO create(CommentDTO commentDTO, Long postId);

    void delete(Long commentId);

}
