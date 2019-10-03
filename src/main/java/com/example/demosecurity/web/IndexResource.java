package com.example.demosecurity.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Controller
public class IndexResource {

    @GetMapping(value = {"/index"})
    public String index(ModelMap map){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal!=null){
            UserDetails userDetails = (UserDetails) principal;
            map.addAttribute("username",userDetails.getUsername());
            map.addAttribute("password",userDetails.getPassword());
            map.addAttribute("authorities",userDetails.getAuthorities());
        }
        return "index";
    }

}  
