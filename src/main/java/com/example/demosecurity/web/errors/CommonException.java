package com.example.demosecurity.web.errors;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 */
public class CommonException extends RuntimeException {
    private ExceptionData data;

    public CommonException(ExceptionData data) {
        super(data.getMessage());
        this.data = data;
    }

    public CommonException(ErrorCode code) {
        super(code.getMessage());
        data = new ExceptionData(code.getCode(), code.getMessage());
    }

    public CommonException(ErrorCode code, String extInfo) {
        super(code.getMessage() + extInfo);
        if (StringUtils.isBlank(extInfo)) {
            data = new ExceptionData(code.getCode(), code.getMessage());
            return;
        }
        data = new ExceptionData(code.getCode(),
                StringUtils.isBlank(code.getMessage()) ? extInfo : code.getMessage() + ":" + extInfo);
    }

    public ExceptionData getData() {
        return data;
    }

    @Override
    public CommonException initCause(Throwable throwable){
        super.initCause(throwable);
        return this;
    }
}  
