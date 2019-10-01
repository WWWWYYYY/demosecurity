package com.example.demosecurity.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Controller
public class IndexResource {

    @GetMapping(value = {"/index"})
    public String index(){
        return "index";
    }

}  
