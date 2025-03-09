package com.chauhan.springbootemployeewebproject.springbootemployeewebproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping(path = "/Hinata")
    public String getMsg(){
        return "Hi Tarun, I am Hinata";
    }
}
