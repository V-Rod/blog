package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by V-Rod on 2/7/17.
 */
@Controller
public class ResumeController {

    @GetMapping("/resume")
    public String viewResume() {
        return "resume/index";

    }
}
