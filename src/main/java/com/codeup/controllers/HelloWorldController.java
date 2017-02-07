package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by V-Rod on 2/7/17.
 */

@Controller
public class HelloWorldController {

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
