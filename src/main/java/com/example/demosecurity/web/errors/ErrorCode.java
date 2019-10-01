package com.example.demosecurity.web.errors;

public enum ErrorCode {
    ERROR(-1,""),
    DATA_EXIST(1,"数据重复"),
    CANNOT_GET_DATA_SOURCE(2,"无法获取数据源"),
    DATA_SOURCE_HAS_INIT(3,"数据源已经初始化"),
    CURRENT_TENANT_CODE_NOT_FOUND(4,"当前租户信息不存在"),
    TENANT_CODE_IS_DIFFERENT(5,"租户编号不一致"),
    PROCESS_FAILED(6, "处理失败"),
    RETURN_TYPE_ERROR(7, "租户返回值类型有误"),
    INVALID_PARAMS(7, "非法参数"),
    USER_NOT_FOUND(9, "用户并存在"),
    PASSWORD_ERROR(10, "密码错误"),
    ;

    private ErrorCode(int code, String message) {
        this.code = code + offset;
        this.message = message;
    }

    private int code;
    private String message;
    private static final int offset = 50000;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
