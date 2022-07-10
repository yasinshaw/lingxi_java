package com.lingxi.lingxi_java.auth.application;

import com.lingxi.lingxi_java.common.enums.PermissionTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePermissionRequest {
    @NotNull
    private PermissionTypeEnum type;
    @NotNull
    private String value;
}
