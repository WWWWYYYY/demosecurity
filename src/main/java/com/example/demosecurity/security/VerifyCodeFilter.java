package com.example.demosecurity.security;

import com.example.demosecurity.utils.VerifyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * TODO
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {

    @Autowired
    private FailureAuthenticationHandler failureAuthenticationHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            if (request.getRequestURI().contains("/login")&& request.getMethod().equals("POST")){
            try {
                final String verifyCode =request.getParameter("verifyCode");
                String key = (String) request.getSession().getAttribute(VerifyUtil.RANDOMCODEKEY);
                if (StringUtils.isEmpty(verifyCode)){
                    throw new CodeAuthenticationException("验证码不能为空");
                }
                if (!verifyCode.equalsIgnoreCase(key)){
                    throw new CodeAuthenticationException("验证码输入错误");
                }

            }catch (AuthenticationException e){
                HttpSession session = request.getSession(false);

                if (session != null ) {
                    session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
                            e);
                }
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                failureAuthenticationHandler.onAuthenticationFailure(request,response,e);
                //重点在错误处理后需要直接return
                return;
            }

        }
        filterChain.doFilter(request,response);
    }
}
