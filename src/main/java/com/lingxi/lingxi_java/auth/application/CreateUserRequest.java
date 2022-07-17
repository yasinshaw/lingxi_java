package com.lingxi.lingxi_java.auth.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {
    private String username;
    private String password;
    private String avatar;
    private String nickName;
}
