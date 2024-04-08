package com.dbarvekar.blog.impl;

import com.dbarvekar.blog.entities.Category;
import com.dbarvekar.blog.entities.Post;
import com.dbarvekar.blog.entities.User;
import com.dbarvekar.blog.exceptions.ResourceNotFoundException;
import com.dbarvekar.blog.payloads.PostDTO;
import com.dbarvekar.blog.payloads.PostResponse;
import com.dbarvekar.blog.repositories.CategoryRepository;
import com.dbarvekar.blog.repositories.PostRepository;
import com.dbarvekar.blog.repositories.UserRepository;
import com.dbarvekar.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public PostDTO create(PostDTO postDTO, Long userId, Long categoryId) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new ResourceNotFoundException("User not found with id %d".formatted(userId));
        });

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            return new ResourceNotFoundException("Category not found with id %d".formatted(categoryId));
        });

        Post post = modelMapper.map(postDTO, Post.class);
        post.setImageName("default.img");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        post = postRepository.save(post);

        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public PostDTO update(PostDTO postDTO) {
        Post post = postRepository.findById(postDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Post not found with id %d".formatted(postDTO.getId())));
        Post updatingPost = modelMapper.map(postDTO, Post.class);
        BeanUtils.copyProperties(updatingPost,post,"id");
        Post save = postRepository.save(post);
        return modelMapper.map(save,PostDTO.class);
    }

    @Override
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id %d".formatted(id)));
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse getAllPost(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {

        Sort sort = Sort.by(sortBy).descending();
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }
        Pageable page = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> all = postRepository.findAll(page);
        List<Post> postList = all.getContent();
        List<PostDTO> postDTOList = postList.stream().map(post -> {
            return modelMapper.map(post, PostDTO.class);
        }).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setTotalElements(all.getTotalElements());
        postResponse.setTotalPages(all.getTotalPages());
        postResponse.setLastPage(all.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id %d".formatted(id)));
        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            return new ResourceNotFoundException("Category not found with id %d".formatted(categoryId));
        });
        return postRepository.findByCategory(category).stream().map(post -> {
            return modelMapper.map(post,PostDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new ResourceNotFoundException("User not found with id %d".formatted(userId));
        });
        return postRepository.findByUser(user).stream().map(post -> {
            return modelMapper.map(post,PostDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPost(String word) {

        List<Post> byTitleContaining = postRepository.findByTitleContaining(word);

        return byTitleContaining.stream().map(post -> {
            return modelMapper.map(post, PostDTO.class);
        }).collect(Collectors.toList());

    }
}
