package com.codeup.repositories;

import com.codeup.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by V-Rod on 2/9/17.
 */
@Repository
public interface PostsRepository extends CrudRepository<Post, Long> {

    public Post findById(Long id);


    // This is a custom query annotation to search the database
    @Query("select p from Post p where p.title like ?1")
    public List<Post> findWhereTitleLike(String title);


}
