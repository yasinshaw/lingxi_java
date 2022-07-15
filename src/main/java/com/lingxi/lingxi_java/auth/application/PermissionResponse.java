package com.lingxi.lingxi_java.auth.application;

import com.lingxi.lingxi_java.common.enums.PermissionTypeEnum;
import lombok.Data;

@Data
public class PermissionResponse {
    private Long id;
    private PermissionTypeEnum type;
    private String value;
}
