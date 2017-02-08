package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by V-Rod on 2/7/17.
 */
@Controller
public class PostsController {

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public String viewAllPosts (Model viewModel) {

        // array list with several Post objects
        List<Post> posts = new ArrayList<>();

        // pass the list to the view (through a view model)
        posts.add(new Post("First Post", "Some content here."));
        posts.add(new Post("Second Post", "Someone."));

        // the name you want to use on the left side, it will be the name you will use in the view
        viewModel.addAttribute("posts", posts );

        // should return posts/index
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewSinglePost (@PathVariable long id, Model viewModel) {
        //Post post = posts.find(id);

        // One Post object, pass the post to the view (through a view model)
        viewModel.addAttribute("post", new Post("First Post", "Some content here"));

        // show the view
        return "posts/show"; // TODO: should return the show view
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreatePostForm () {

        return "<h1>Form For Create Posts</h1>";
    }

    @PostMapping("posts/create")
    @ResponseBody
    public String createPost(){
        return "Create a new post";
    }
}
