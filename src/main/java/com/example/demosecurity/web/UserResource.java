package com.example.demosecurity.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@RequestMapping("/user")
@Controller
public class UserResource {

    @GetMapping(value = {"/index"})
    public String index(){
        return "user/index";
    }

}  
