package com.dbarvekar.blog.repositories;

import com.dbarvekar.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
