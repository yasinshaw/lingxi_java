package com.lingxi.lingxi_java.common;

public class Constants {
    public static final String AUTH_PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String[] AUTH_WHITELIST = {
            "/admin/login",
            "/admin/currentUserInfo",
            // -- swagger ui
            "/swagger**/**",
            "/javainuse-openapi/**",
            "**/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/v3/**"
    };
}
