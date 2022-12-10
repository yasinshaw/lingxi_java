package com.lingxi.lingxi_java.milestone.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateMileStoneRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private LocalDateTime time;
    @NotBlank
    private String picUrls;
    @NotNull
    private Boolean portalPublic;
}
