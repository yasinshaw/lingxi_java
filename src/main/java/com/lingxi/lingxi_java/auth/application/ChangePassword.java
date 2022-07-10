package com.lingxi.lingxi_java.auth.application;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePassword {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
