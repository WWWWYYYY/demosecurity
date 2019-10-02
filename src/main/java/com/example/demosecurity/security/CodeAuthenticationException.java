package com.example.demosecurity.security;

import org.springframework.security.core.AuthenticationException;

/**
 * 基于security 框架的自定义异常类
 */
public class CodeAuthenticationException extends AuthenticationException {

    public CodeAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CodeAuthenticationException(String msg) {
        super(msg);
    }
}