package com.lingxi.lingxi_java.auth.application;

import lombok.Data;

@Data
public class UpdateCurrentUserInfoRequest {
    private String nickName;
    private String avatar;
}
