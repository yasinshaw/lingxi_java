package com.lingxi.lingxi_java.auth.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotNull
    private Long userId;
    @NotBlank
    private String avatar;
    @NotBlank
    private String nickName;
}
