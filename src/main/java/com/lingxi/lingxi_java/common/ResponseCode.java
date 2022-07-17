package com.lingxi.lingxi_java.common;

import lombok.Getter;

@Getter
public enum ResponseCode {

    // 10000 - 19999 通用
    SYSTEM_ERROR(10000, "系统异常"),
    LOGIN_FAILED(10001, "登录失败，请检查用户名或密码！"),
    NO_PERMISSION(10002, "无权限，请联系管理员授权！"),
    ACCESS_EXPIRED_ERROR(10003, "登录状态已失效，请重新登录！"),
    TOKEN_CHECK_ERROR(10004, "登录状态校验失败，请重新登录！"),
    NO_USER(10005, "查找用户失败"),
    REPEAT_USERNAME(10006, "该用户已存在，用户名不能重复！"),
    ;

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseCode findByCode(Integer code) {
        for (ResponseCode item : values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

}
