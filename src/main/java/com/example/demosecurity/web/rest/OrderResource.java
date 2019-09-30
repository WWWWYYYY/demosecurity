package com.example.demosecurity.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 */
@Controller
@RequestMapping("/order")
public class OrderResource {

    @RequestMapping("/indexPage")
    public String indexPage(){
        return "order/index";
    }
    @RequestMapping("/addPage")
    public String addPage(){
        return "order/add";
    }
}  
