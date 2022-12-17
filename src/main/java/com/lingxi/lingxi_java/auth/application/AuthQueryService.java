package com.lingxi.lingxi_java.auth.application;

import com.lingxi.lingxi_java.auth.AuthMapper;
import com.lingxi.lingxi_java.auth.domain.*;
import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.common.enums.PermissionTypeEnum;
import com.lingxi.lingxi_java.common.exceptions.BizException;
import com.lingxi.lingxi_java.utils.AuthenticationUtil;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthQueryService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PermissionRepository permissionRepository;

    public UserInfoResponse getCurrentUserInfo() {
        Long userId = AuthenticationUtil.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new BizException(ResponseCode.NO_USER));
        Set<Role> roles = user.getRoles();
        List<Permission> permissions = permissionRepository.findAllByUserId(userId);
        AuthMapper mapper = AuthMapper.mapper;
        UserInfoResponse userInfoResponse = mapper.userEntity2Response(user);
        userInfoResponse.setRoles(roles.stream().map(mapper::roleEntity2Response).collect(Collectors.toList()));
        userInfoResponse.setPermissions(permissions.stream().map(mapper::permissionEntity2Response).collect(Collectors.toList()));
        return userInfoResponse;
    }

    public Page<PermissionInfoResponse> getPermissionList(Pageable pageable, PermissionTypeEnum type) {
        if (type == null) {
            return permissionRepository.findAll(pageable).map(AuthMapper.mapper::permissionEntity2Response);
        }
        Page<Permission> permissions = permissionRepository.findAllPageableByType(pageable, type);
        return permissions.map(AuthMapper.mapper::permissionEntity2Response);
    }

    public Page<PermissionInfoResponse> getPermissionListByRoleId(Pageable pageable, Long roleId) {
        Page<Permission> permissions = permissionRepository.findAllByRoleId(pageable, roleId);
        return permissions.map(AuthMapper.mapper::permissionEntity2Response);
    }

    public Page<RoleInfoResponse> getRoleList(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        return roles.map(AuthMapper.mapper::roleEntity2Response);
    }

    public Page<RoleInfoResponse> getRoleListByPermissionId(Pageable pageable, Long permissionId) {
        Page<Role> roles = roleRepository.findAllByPermissionId(pageable, permissionId);
        return roles.map(AuthMapper.mapper::roleEntity2Response);
    }

    public Page<RoleInfoResponse> getRoleListByUserId(Pageable pageable, Long userId) {
        Page<Role> roles = roleRepository.findAllByUserId(pageable, userId);
        return roles.map(AuthMapper.mapper::roleEntity2Response);
    }

    public Page<UserListResponse> getUserList(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(AuthMapper.mapper::userEntity2ListResponse);
    }

    public Page<UserListResponse> getUserListByRoleId(Pageable pageable, Long roleId) {
        Page<User> users = userRepository.findAllByRoleId(pageable, roleId);
        return users.map(AuthMapper.mapper::userEntity2ListResponse);
    }
}
