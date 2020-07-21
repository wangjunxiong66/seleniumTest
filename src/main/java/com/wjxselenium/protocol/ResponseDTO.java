package com.wjxselenium.protocol;

import com.wjxselenium.enums.ResponseCode;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/7/21 上午10:36
 */
public class ResponseDTO<T> {
    private int code;
    private String message;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResponseDTO OK(Object data){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ResponseCode.SUCCESS.getCode());
        responseDTO.setMessage("请求成功");
        responseDTO.setData(data);
        return responseDTO;
    }

    public static ResponseDTO OK(){
        return OK(null);
    }

    public boolean isSuccess(){
        return code == ResponseCode.SUCCESS.getCode();
    }

    //  系统异常
    public static ResponseDTO systemError(String message){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
        responseDTO.setMessage(message);
        return responseDTO;
    }

    public static ResponseDTO systemError(){
        return systemError(ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
    }


}
