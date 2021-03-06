package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.repositories.PostsRepository;
import com.codeup.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Created by V-Rod on 2/7/17.
 */
@Controller
public class PostsController {

    @Value("${uploads}")  // this code is referencing the application.properties file
    private String uploadsPath;

//    @Autowired
//    PostService postService;  //connecting the service to the controller which is a middleman between the database and the controller

    @Autowired
    PostsRepository postDao;

    @Autowired
    UserSvc userSvc;

    @GetMapping("/posts")
    public String viewAllPosts (Model viewModel) {

        viewModel.addAttribute("posts", Collections.emptyList() );

        // should return posts/index
        return "posts/index";
    }

    @GetMapping("/posts.json")
    public @ResponseBody List<Post> retrieveAllAds() {
        return (List<Post>) postDao.findAll();

    }

    @GetMapping("/posts/{id}")
    public String viewSinglePost (@PathVariable long id, Model viewModel) {

        // One Post object, pass the post to the view (through a view model)
        viewModel.addAttribute("post", postDao.findOne(id) );

        // show the view
        return "posts/show"; // TODO: should return the show view
    }

    @GetMapping("/posts/create")
    public String viewCreatePostForm (@ModelAttribute Post post, Model viewModel) {
        viewModel.addAttribute("post", post);
        return "posts/create";
    }

    @PostMapping("posts/create")
    public String createPost(
            @Valid Post post, // @Valid calls ModelAttribute first
            Errors validation,
            Model viewModel,
            @RequestParam(name = "image_file")MultipartFile uploadedFile  // the parameter name has to be the same here
                                                                         // and in the view
    ){

        // if there are errors on validation, the viewModel will take you back to the form
        if (validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("post", post);
            return "posts/create";
        }

        String filename = uploadedFile.getOriginalFilename();
        //System.out.println(filename);
        String filepath = Paths.get(uploadsPath, filename).toString();
        //System.out.println(filepath);
        File destinationFile = new File(filepath);

        try {
            uploadedFile.transferTo(destinationFile);  // it will move the file in this step
            viewModel.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            viewModel.addAttribute("message", "Oops! Something went wrong! " + e);
        }

        // get this from session
        // User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(userSvc.loggedInUser());
        post.setImage(filename);  // displaying the image for the user if they uploaded an image
        //save the ad means calling the service and using the save method from the service
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post, Model viewModel) {
        //viewModel.addAttribute("post", postDao.findOne(id));
        Post editedPost = postDao.findOne(id);
        viewModel.addAttribute("post", editedPost);
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost (@Valid Post editedPost, Errors validation, Model viewModel) {

        if (validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("post", editedPost);
            return "posts/edit";
        }

        postDao.save(editedPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/delete")
    public String deletePost(@ModelAttribute Post post) {
        postDao.delete(postDao.findOne(post.getId()));
        return "redirect:/posts";
    }
}

//    @PostMapping("/posts/search")
//    public String search(@RequestParam(name="term") String term, Model viewModel) {
//        viewModel.addAttribute("posts", postDao.findByBodyIsLike(term));
//        return "posts/index";
//    }

