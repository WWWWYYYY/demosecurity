package com.example.demosecurity.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 根据情况选择
 */
@Controller
//@RestController
public class ErrorResource {

    @RequestMapping("/error403")
    public String error403(){
        return "403";
    }
}  
