package com.lingxi.lingxi_java.auth.domain;

import com.lingxi.lingxi_java.common.BaseEntity;
import com.lingxi.lingxi_java.common.enums.PermissionTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "permission")
public class Permission extends BaseEntity {
    private PermissionTypeEnum type;
    private String value;

    public static Permission create(PermissionTypeEnum type, String value) {
        Permission permission = new Permission();
        permission.type = type;
        permission.value = value;
        return permission;
    }

    public void update(PermissionTypeEnum type, String value) {
        this.type = type;
        this.value = value;
    }
}
