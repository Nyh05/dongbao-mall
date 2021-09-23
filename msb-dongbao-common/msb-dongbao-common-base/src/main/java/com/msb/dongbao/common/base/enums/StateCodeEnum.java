package com.msb.dongbao.common.base.enums;

public enum StateCodeEnum {
    /**
     * 登录成功
     */
    SUCCESS(200,"登录成功"),
    FAIL(500,"登陆失败");

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
