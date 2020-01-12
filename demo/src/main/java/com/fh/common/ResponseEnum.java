package com.fh.common;

public enum ResponseEnum {

    USERNAME_IS_NULL(1000,"用户名不存在"),
    PASSWORD_IS_ERROR(1001,"密码错误");

    private int code;

    private String msg;

    private ResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
