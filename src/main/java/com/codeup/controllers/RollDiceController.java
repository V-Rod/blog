package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by V-Rod on 2/8/17.
 */
@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String showLinksWithNumbers() {

        return "roll-dice/index";
    }

    @GetMapping("roll-dice/{n}")
    public String compareGuess(@PathVariable int n, Model viewModel) {  // The Model here is the View Model

        // Generate a random number between 1 and 6
        int rollDice = (int) (Math.random() * 6 + 1);


        // Passing the data to the view
        viewModel.addAttribute("n", n);
        viewModel.addAttribute("rollDice", rollDice);

        // Return a new view
        return "roll-dice/roll-dice-result";
    }


}
