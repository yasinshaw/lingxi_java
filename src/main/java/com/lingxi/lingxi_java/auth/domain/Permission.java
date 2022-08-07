package com.lingxi.lingxi_java.auth.domain;

import com.lingxi.lingxi_java.common.BaseEntity;
import com.lingxi.lingxi_java.common.enums.PermissionTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "permission")
public class Permission extends BaseEntity {
    private PermissionTypeEnum type;
    private String value;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "role_permission_relation",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            joinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

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

    public void updateRoles(HashSet<Role> roles) {
        this.roles = roles;
    }
}
