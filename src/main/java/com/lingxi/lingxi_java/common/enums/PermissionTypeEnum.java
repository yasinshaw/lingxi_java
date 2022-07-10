package com.lingxi.lingxi_java.common.enums;

import com.lingxi.lingxi_java.common.exceptions.BizException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum PermissionTypeEnum {
    API(1, "接口权限"),
    MENU(2, "菜单权限"),
    CUSTOM(3, "自定义权限");

    private Integer value;
    private String description;

    PermissionTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static PermissionTypeEnum valueOf(Integer value) {
        return Arrays.stream(PermissionTypeEnum.values()).filter(x -> Objects.equals(x.value, value)).findAny().orElseThrow(() -> new BizException("转换权限类型失败"));
    }

}
