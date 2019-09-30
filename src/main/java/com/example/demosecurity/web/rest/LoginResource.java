package com.example.demosecurity.web.rest;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: TODO  </p>
 * <p>Company: TaoQiCar Co., Ltd.             </p>
 * <p>Create Time: 2019/9/30      </p>
 * <p>Description: TODO  </p>
 *
 * @author wangyi
 * @email wangyi@xxfqc.com
 * <p>Update Time:                      </p>
 * <p>Updater:                          </p>
 * <p>Update Comments:                  </p>
 */
@Controller
public class LoginResource {

    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,ModelMap map){
        map.addAttribute("error",error);
        return "login";
    }

    @PostMapping("/login")
    public String login(String username,String password){

        System.out.println(username+":"+password);
        return "index";
    }

}  
