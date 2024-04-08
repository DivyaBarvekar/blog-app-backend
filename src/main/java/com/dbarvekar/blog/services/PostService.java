package com.dbarvekar.blog.services;

import com.dbarvekar.blog.payloads.PostDTO;
import com.dbarvekar.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO create(PostDTO postDTO, Long userId, Long categoryId);

    PostDTO update(PostDTO postDTO);

    void delete(Long id);

    PostResponse getAllPost(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);

    PostDTO getPostById(Long id);

    List<PostDTO> getPostByCategory(Long categoryId);

    List<PostDTO> getPostByUser(Long userId);

    List<PostDTO> searchPost(String word);

}
