package com.lingxi.lingxi_java.auth.application;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdatePermissionRoleRelationRequest {
    @NotNull
    private Long permissionId;
    @NotNull
    private List<Long> roleIds;
}
