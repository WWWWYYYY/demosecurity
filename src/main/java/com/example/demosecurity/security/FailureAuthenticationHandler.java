package com.example.demosecurity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Component
public class FailureAuthenticationHandler extends SimpleUrlAuthenticationFailureHandler {
    protected Logger logger=LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper =new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");
//        throw new CodeAuthenticationException(exception.getMessage());
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
                exception);
        response.sendRedirect("/login?error=true");
    }
}
