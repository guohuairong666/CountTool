package com.urun.counttool.model.entity;


/**
 * Created by Administrator on 2017/11/1.
 */
public class ResultDto {
    private int code;
    private Object data;
    private String message;
    private boolean succeed;

    public ResultDto(int code,String message,boolean succeed) {
        this.code = code;
        this.message = message;
        this.succeed = succeed;
    }


    public ResultDto() {

    }
    public ResultDto(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }
}
