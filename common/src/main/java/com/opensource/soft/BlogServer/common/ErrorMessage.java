package com.opensource.soft.BlogServer.common;

/**
 * Created by shasha on 2017/7/16.
 */
public enum ErrorMessage {

    SUCCESS(0,"成功"),
    ERR_SYSTERM(1,"系统错误"),
    ERR_PARAM_ERROR(2,"参数错误"),
    ERR_PARAM_NO_FIND(3,"参数%s没有发现"),
	ERR_SHIRO_NO_LOGIN(4,"用户没有登录");

    private int code;
    private String message;

    ErrorMessage(int code ,String message){
        this.code = code;
        this.message = message;
    }

    public static String getName(int code,Object... args) {
        for (ErrorMessage e : ErrorMessage.values()) {
            if (e.getCode() == code){
                return String.format(e.getMessage(),args);
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

