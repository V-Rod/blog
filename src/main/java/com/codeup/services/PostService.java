package com.codeup.services;

import com.codeup.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by V-Rod on 2/8/17.
 */
@Service("postService")
public class PostService {

    private List<Post> posts = new ArrayList<>();

    public PostService() {
        // This runs by the time the class is created
        createPosts();
    }

    public Post save (Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post findOne(long id) {
        return posts.get((int) id - 1);
    }

    private void createPosts() {
        // create some post objects and add them to the posts list with the save method

        for (int i = 0; i < 100; i++) {
            save(new Post("title" + (i + 1), "Some body content" + (i + 2) ));
        }

    }
}
