package com.example.demosecurity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
public class AsynFailureAuthenticationHandler extends FailureAuthenticationHandler {
    protected Logger logger=LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper =new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");
        Map data =new HashMap();
        data.put("success",false);
        data.put("msg", exception.getMessage());
        String string = objectMapper.writeValueAsString(data);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(string);
    }
}
