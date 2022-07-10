package com.lingxi.lingxi_java.common.dto;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

@Getter
// todo yasin 这里看下可不可以直接实现接口
public class MyAuthenticationToken extends AbstractAuthenticationToken {
    private final String credentials;

    private final Long principal;

    public MyAuthenticationToken(String credentials, Long principal) {
        super(Collections.emptyList());
        this.credentials = credentials;
        this.principal = principal;
        setAuthenticated(this.principal != null);
    }
}
