package com.example.demosecurity.web.errors;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务异常
 *
 * @author lijinzhou
 * @since 2019/8/21 15:03
 */
public class MyException extends CommonException {

    public MyException(ExceptionData data) {
        super(data);
    }
    public static MyException build(ErrorCode code) {
        ExceptionData data = new ExceptionData(code.getCode(), code.getMessage());
        return new MyException(data);
    }

    public static MyException build(ErrorCode code, String extInfo) {
        ExceptionData data;
        if (StringUtils.isBlank(extInfo)) {
            data = new ExceptionData(code.getCode(), code.getMessage());
        } else {
            data = new ExceptionData(code.getCode(),
                    StringUtils.isBlank(code.getMessage()) ? extInfo : code.getMessage() + ":" + extInfo);
        }
        return new MyException(data);
    }

    public static void  buildAndThrow(ErrorCode code, String extInfo){
        throw build(code, extInfo);
    }
    public static void  buildAndThrow(ErrorCode code){
        throw build(code);
    }

}
