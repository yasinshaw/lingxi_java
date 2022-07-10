package com.lingxi.lingxi_java.common.handlers;

import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.common.ResponseResult;
import com.lingxi.lingxi_java.common.exceptions.BizException;
import com.lingxi.lingxi_java.utils.ObjectMapperUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalResponseAdviceHandler implements ResponseBodyAdvice<Object> {

    @ExceptionHandler(BizException.class)
    public ResponseResult<Void> bizExceptionHandler(HttpServletRequest req, BizException e) {
        return ResponseResult.fail(e);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseResult<Void> exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return ResponseResult.fail(ResponseCode.SYSTEM_ERROR);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseResult<?>) {
            return body;
        }
        if (body instanceof String) {
            return ObjectMapperUtil.getNewInstance().writeValueAsString(ResponseResult.success(body));
        }
        return ResponseResult.success(body);
    }
}
