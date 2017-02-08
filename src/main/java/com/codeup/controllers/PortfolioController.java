package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by V-Rod on 2/7/17.
 */
@Controller
public class PortfolioController {

    @GetMapping("/portfolio")
    public String viewPortfolio() {
        return "portfolio/index";

    }
}
