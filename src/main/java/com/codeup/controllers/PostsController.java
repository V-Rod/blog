package com.codeup.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by V-Rod on 2/7/17.
 */
public class PostsController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsIndex () {
        return "<h1>Posts Index Page</h1>";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postsById () {
        return "<h1>View Posts By ID</h1>";
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String postFormView () {
        return "<h1>Form For Create Posts</h1>";
    }

    @PostMapping("posts/create")
    @ResponseBody
    public String postCreateNew(){
        return postCreateNew();
    }
}
