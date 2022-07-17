package com.lingxi.lingxi_java.auth;

import com.lingxi.lingxi_java.auth.application.PermissionInfoResponse;
import com.lingxi.lingxi_java.auth.application.RoleInfoResponse;
import com.lingxi.lingxi_java.auth.application.UserInfoResponse;
import com.lingxi.lingxi_java.auth.application.UserListResponse;
import com.lingxi.lingxi_java.auth.domain.Permission;
import com.lingxi.lingxi_java.auth.domain.Role;
import com.lingxi.lingxi_java.auth.domain.User;
import com.lingxi.lingxi_java.common.enums.PermissionTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
    AuthMapper mapper
            = Mappers.getMapper(AuthMapper.class);

    UserInfoResponse userEntity2Response(User user);

    UserListResponse userEntity2ListResponse(User user);

    RoleInfoResponse roleEntity2Response(Role role);

    PermissionInfoResponse permissionEntity2Response(Permission permission);

    default PermissionTypeEnum map(Integer value) {
        return PermissionTypeEnum.valueOf(value);
    }

    default Integer map(PermissionTypeEnum value) {
        return value.getValue();
    }
}
