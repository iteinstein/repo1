package com.fh.model;

public enum ResponseEnum {
    OK(200,"操作成功"),
    ERROR(500,"操作失败"),
    PHONE_NUMBER_IS_NULL(505,"手机号为空"),
    MEMBER_INFO_IS_NULL(506,"会员信息为空"),
    MEMBER_USERNAME_IS_NULL(507,"会员用户名为空"),
    MEMBER_PASSWORD_IS_NULL(508,"会员密码为空"),
    MEMBER_PHONE_NUMBER_IS_NULL(509,"会员手机号为空"),
    MEMBER_PHONE_NUMBER_IS_ILLEGAL(510,"会员手机号不合法"),
    VERIFY_CODE_IS_NULL(511,"验证码为空"),
    VERIFY_CODE_IS_EXPIRED(512,"验证码无效"),
    VERIFY_CODE_IS_ERROR(513,"验证码错误"),
    MEMBER_USERNAME_IS_EXISTED(514,"用户名已存在"),
    MEMBER_PHONE_NUMBER_IS_USED(515,"手机号已注册"),
    PWD_ERROR(403,"密码");

    private int code;

    private String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
