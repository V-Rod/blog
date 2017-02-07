package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by V-Rod on 2/7/17.
 */
@Controller
public class MathController {

    @GetMapping("/add/{number1}/and/{number2}")
    @ResponseBody
    public String add(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " plus  " + number2 + " equals " + (number1 + number2);
    }

    @GetMapping("/subtract/{number1}/from/{number2}")
    @ResponseBody
    public String subtract(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " minus " + number2 + " equals " + (number1 - number2);
    }

    @GetMapping("multiply/{number1}/and/{number2}")
    @ResponseBody
    public String multiply(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " times " + number2 + " equals " + (number1*number2);
    }

    @GetMapping("divide/{number1}/by/{number2}")
    @ResponseBody
    public String divide(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " divided by " + number2 + " equals " + (number1/number2);
    }
}
