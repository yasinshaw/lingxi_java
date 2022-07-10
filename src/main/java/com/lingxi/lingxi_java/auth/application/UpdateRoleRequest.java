package com.lingxi.lingxi_java.auth.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRoleRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String code;
    @NotBlank
    private String name;
}
