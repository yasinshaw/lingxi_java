package com.lingxi.lingxi_java.auth.application;

import com.lingxi.lingxi_java.common.enums.PermissionTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "权限")
public class PermissionInfoResponse {
    private Long id;
    private PermissionTypeEnum type;
    private String value;
}
