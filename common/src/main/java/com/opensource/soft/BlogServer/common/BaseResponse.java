package com.opensource.soft.BlogServer.common;

import com.alibaba.fastjson.JSON;

/**
 * Created by shasha on 2017/7/16.
 */
public class BaseResponse {
    private int code;
    private String message;
    private Object data;

    public BaseResponse() {
    }

    public BaseResponse(int code ) {
        this.code = code;
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 返回无参数正确json
     * @return
     */
    public static String successJson(){
        return new BaseResponse(ErrorMessage.SUCCESS.getCode(),ErrorMessage.SUCCESS.getMessage()).toJson();
    }
    
    /**
     * 返回有参数正确json
     * @param data 
     * @return
     */
    public static String successJson(Object data){
        return new BaseResponse(ErrorMessage.SUCCESS.getCode(),ErrorMessage.SUCCESS.getMessage(),data).toJson();
    }
    
    /**
     * 返回错误信息
     * @param em
     * @param args
     * @return
     */
    public static String errorJson(ErrorMessage em,Object... args){
        return new BaseResponse(em.getCode(),String.format(em.getMessage(), args)).toJson();
    }

    
    public String toJson() {
        return JSON.toJSONString(this);
    }

    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
    
}