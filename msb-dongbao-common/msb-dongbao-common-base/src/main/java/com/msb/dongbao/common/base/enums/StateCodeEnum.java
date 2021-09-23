package com.msb.dongbao.common.base.enums;

public enum StateCodeEnum {
    /**
     * 登录成功
     */
    SUCCESS(200,"请求成功"),
    FAIL(500,"请求失败"),

    PASSWORD_ERROR(1001,"用户名密码不正确"),
    USER_EMPTY(1002,"用户不存在");






    private int code;
    private String message;

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

    StateCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
