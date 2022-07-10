package com.lingxi.lingxi_java.common;

import com.lingxi.lingxi_java.common.exceptions.BizException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一结果返回
 *
 * @param <T> 结果数据类型
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseResult<T> {

    private int code = 0;

    private String message;

    private T data;

    public static <T> ResponseResult<T> successWithEmpty() {
        return success(null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(0, "ok", data);
    }

    public static <T> ResponseResult<T> fail(int code, String message) {
        return new ResponseResult<>(code, message, null);
    }

    public static <T> ResponseResult<T> fail(ResponseCode responseCode) {
        return new ResponseResult<>(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> ResponseResult<T> fail(BizException bizException) {
        return new ResponseResult<>(bizException.getCode(), bizException.getMessage(), null);
    }
}
