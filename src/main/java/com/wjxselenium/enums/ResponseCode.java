package com.wjxselenium.enums;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/7/21 上午9:36
 */
public enum ResponseCode {

    SUCCESS(1,"OK"),
    INTERNAL_SERVER_ERROR(500, "服务器开小差了，请稍后再试！"),
    ;

    private int code;
    private String message;

    ResponseCode(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

}
