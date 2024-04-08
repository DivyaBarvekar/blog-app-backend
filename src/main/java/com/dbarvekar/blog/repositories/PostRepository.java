package com.dbarvekar.blog.repositories;

import com.dbarvekar.blog.entities.Category;
import com.dbarvekar.blog.entities.Post;
import com.dbarvekar.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

//    @Query("select p from post p where p.title like %:keyword%")
//    List<Post> searchBYTitle(@Param("key") String keyword);

}
