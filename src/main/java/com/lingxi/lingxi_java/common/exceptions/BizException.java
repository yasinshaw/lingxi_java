package com.lingxi.lingxi_java.common.exceptions;

import com.lingxi.lingxi_java.common.ResponseCode;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException{
    protected int code;
    protected String message;

    public BizException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(String message) {
        this.code = ResponseCode.SYSTEM_ERROR.getCode();
        this.message = message;
    }

    public BizException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
