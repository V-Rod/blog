package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by V-Rod on 2/7/17.
 */

@Controller
public class HelloWorldController {

    @GetMapping("/home")
    public String homePage (Model model) {

        List<String> names = new ArrayList<>();
        names.add("Fer");
        names.add("Fer");
        names.add("Fer");

        // Passing all the attributes to my view
        // This is a String
        model.addAttribute("date", "Feb 7th");
        // This is an Integer
        model.addAttribute("age", 38);
        // This is a list
        model.addAttribute("names", names);

        return "home"; // template we want to use located in templates directory
    }

    @GetMapping("/contact")
    public String contactPage () {
        return "contact/form"; // template we want to use located in templates directory/contact subdirectory
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String hello(@PathVariable String name) {
        return "<h1>Hello " + name + " from Spring!!!</h1>";
    }

    @RequestMapping(value = "/bye/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String bye(@PathVariable String name) {
        return "<h1>Goodbye " + name + " from Spring!!!</h1>";
    }

}
