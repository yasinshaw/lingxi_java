package com.lingxi.lingxi_java.auth.application;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUserRoleRelationRequest {
    @NotNull
    private Long userId;
    @NotNull
    private List<Long> roleIds;
}
