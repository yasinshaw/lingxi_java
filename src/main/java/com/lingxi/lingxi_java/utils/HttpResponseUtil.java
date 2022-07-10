package com.lingxi.lingxi_java.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.common.ResponseResult;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HttpResponseUtil {
  public static void successResponse(HttpServletResponse response) throws IOException {
    successResponse(response, null);
  }

  public static  <T> void successResponse(HttpServletResponse response, T data) throws IOException {
    response(response, ResponseResult.success(data));
  }

  public static void failureResponse(HttpServletResponse response) throws IOException {
    failureResponse(response, ResponseCode.SYSTEM_ERROR);
  }

  public static void failureResponse(HttpServletResponse response, ResponseCode errorCode) throws IOException {
    response(response, ResponseResult.fail(errorCode.getCode(), errorCode.getMessage()));
  }

  private static <T> void response(HttpServletResponse response, ResponseResult<T> result) throws IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");
    PrintWriter writer = response.getWriter();
    ObjectMapper objectMapper = new ObjectMapper();
    writer.append(objectMapper.writeValueAsString(result));
  }
}
