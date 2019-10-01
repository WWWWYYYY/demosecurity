package com.example.demosecurity.web.errors;

import java.io.Serializable;

/**
 * TODO
 */
public class ExceptionData implements Serializable {

    private int errorCode;
    private String message;

    public ExceptionData(int errorCode, String msg) {
        this.errorCode = errorCode;
        this.message = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}