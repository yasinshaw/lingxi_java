package com.lingxi.lingxi_java.common.handlers;

import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.utils.HttpResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthAccessDeniedHandler extends HttpResponseUtil implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException {
        failureResponse(response, ResponseCode.NO_PERMISSION);
    }

}
