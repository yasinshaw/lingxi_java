package com.lingxi.lingxi_java.auth.application;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdateRoleUserRelationRequest {
    @NotNull
    private Long roleId;
    @NotNull
    private List<Long> userIds;
}
