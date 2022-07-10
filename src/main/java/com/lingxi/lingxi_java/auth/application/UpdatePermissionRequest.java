package com.lingxi.lingxi_java.auth.application;

import com.lingxi.lingxi_java.common.enums.PermissionTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePermissionRequest {
    @NotNull
    private Long id;
    @NotNull
    private PermissionTypeEnum type;
    @NotNull
    private String value;
}
