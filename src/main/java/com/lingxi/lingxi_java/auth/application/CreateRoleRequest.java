package com.lingxi.lingxi_java.auth.application;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRoleRequest {
    @NotBlank
    private String code;
    @NotBlank
    private String name;
}
