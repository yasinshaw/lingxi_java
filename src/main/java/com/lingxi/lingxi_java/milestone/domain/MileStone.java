package com.lingxi.lingxi_java.milestone.domain;

import com.lingxi.lingxi_java.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "mile_stone")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MileStone extends BaseEntity {
    private String title;
    private String description;
    private LocalDateTime time;
    private String picUrls;
    private Boolean portalPublic; // 是否门户对可见

    public static MileStone create(String title, String description, LocalDateTime time, String picUrls, Boolean portalPublic) {
        MileStone mileStone = new MileStone();
        mileStone.setTitle(title);
        mileStone.setDescription(description);
        mileStone.setTime(time);
        mileStone.setPicUrls(picUrls);
        mileStone.setPortalPublic(portalPublic);
        return mileStone;
    }

    public void update(String title, String description, LocalDateTime time, String picUrls, Boolean portalPublic) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.picUrls = picUrls;
        this.portalPublic = portalPublic;
    }

}
