package com.example.miscellaneous.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/home")
public class HomeController {
    @GetMapping("")
    public String hello(){
        return "<div><h1>hello world</h1></div><p>hello world</p>";
    }

}