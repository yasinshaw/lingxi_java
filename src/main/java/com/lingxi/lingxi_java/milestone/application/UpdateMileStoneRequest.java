package com.lingxi.lingxi_java.milestone.application;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateMileStoneRequest {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime time;
    private String picUrls;
    private Boolean portalPublic;
}
