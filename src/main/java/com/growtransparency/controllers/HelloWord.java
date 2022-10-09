package com.growtransparency.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {

    @GetMapping("/helloword")
    public String hello() {
        return "JUCAS GOSTOSO";
    }
}
