package com.example.demosecurity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理器
 * 如果在登录之前有访问了某个资源，登录成功后就会跳转到原来的资源页面上
 * 如果没有返回到首页
 */
@Component
public class AsynSuccessAuthenticationHandler extends SuccessAuthenticationHandler {
    protected Logger logger=LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper =new ObjectMapper();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        RequestCache requestCache = new HttpSessionRequestCache();
        String url = null;
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            url = savedRequest.getRedirectUrl();
        }
        if(url == null){
//            getRedirectStrategy().sendRedirect(request,response,"/index");
            //首页地址
            url="";
        }
        Map data =new HashMap();
        data.put("success",true);
        data.put("url",url);
        String string = objectMapper.writeValueAsString(data);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(string);
    }

}