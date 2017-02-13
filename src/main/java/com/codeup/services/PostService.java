package com.codeup.services;

import com.codeup.models.Post;
import com.codeup.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by V-Rod on 2/8/17.
 */
@Service("postService")
public class PostService {

    private List<Post> posts = new ArrayList<>();
    private PostsRepository repository;

    @Autowired
    public PostService(PostsRepository repository) {
        this.repository = repository;
    }

    // method to create some post objects and add them to the posts list with the save method
    private void createPosts() {
        for (int i = 0; i < 100; i++) {
            save(new Post(i + 1, "title" + " " + (i + 1), "Some body content" + " " +(i + 2) ));
        }
    }

    // This runs by the time the class is created
    public PostService() {
        createPosts();
    }

    public void save (Post post) {
        repository.save(post); // inserts into post (title, description) values (?, ?)

    }

    // retrieving all the posts
    public List<Post> findAll() {
        // Converts the Iterable to a List by casting it
        return (List<Post>) repository.findAll(); // this is doing select * from posts and its generating an array list for posts

    }

    // retrieving an individual post object
    public Post findOne(int id) {
        return posts.get( id - 1) ;
    }


}
