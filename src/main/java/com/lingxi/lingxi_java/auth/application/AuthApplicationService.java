package com.lingxi.lingxi_java.auth.application;

import com.lingxi.lingxi_java.auth.domain.*;
import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.common.exceptions.BizException;
import com.lingxi.lingxi_java.utils.AuthenticationUtil;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthApplicationService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PermissionRepository permissionRepository;

    @Transactional
    public String login(LoginRequest request) {
        User user = userRepository.findOneByUsername(request.getUsername()).orElseThrow(() -> new BizException(ResponseCode.LOGIN_FAILED));
        return user.login(request.getPassword());
    }

    @Transactional
    public Long createRole(CreateRoleRequest request) {
        Role role = Role.create(request.getCode(), request.getName());
        roleRepository.save(role);
        return role.getId();
    }

    @Transactional
    public void updateRole(UpdateRoleRequest request) {
        Role role = roleRepository.findById(request.getId()).orElseThrow(() -> new BizException("查找角色失败"));
        role.update(request.getCode(), request.getName());
        roleRepository.save(role);
    }

    @Transactional
    public Long createPermission(CreatePermissionRequest request) {
        Permission permission = Permission.create(request.getType(), request.getValue());
        permissionRepository.save(permission);
        return permission.getId();
    }

    @Transactional
    public void updatePermission(UpdatePermissionRequest request) {
        Permission roleEntity = permissionRepository.findById(request.getId()).orElseThrow(() -> new BizException("查找权限失败"));
        roleEntity.update(request.getType(), request.getValue());
        permissionRepository.save(roleEntity);
    }

    @Transactional
    public void updateCurrentUserInfo(UpdateCurrentUserInfoRequest request) {
        Long currentUserId = AuthenticationUtil.getCurrentUserId();
        User user = userRepository.findById(currentUserId).orElseThrow(() -> new BizException(ResponseCode.NO_USER));
        user.updateUserInfo(request.getNickName(), request.getAvatar());
        userRepository.save(user);
    }

    @Transactional
    public void updateUserRoleRelation(UpdateUserRoleRelationRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new BizException(ResponseCode.NO_USER));
        List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        user.updateUserRoleRelation(new HashSet<>(roles));
        userRepository.save(user);
    }

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        Long currentUserId = AuthenticationUtil.getCurrentUserId();
        User user = userRepository.findById(currentUserId).orElseThrow(() -> new BizException(ResponseCode.NO_USER));
        user.changePassword(request.getOldPassword(), request.getNewPassword());
        userRepository.save(user);
    }

    public void createUser(CreateUserRequest request) {
        Optional<User> existedUser = userRepository.findOneByUsername(request.getUsername());
        if (existedUser.isPresent()) {
            throw new BizException(ResponseCode.REPEAT_USERNAME);
        }
        User user = User.create(request.getUsername(), request.getPassword(), request.getAvatar(), request.getNickName());
        userRepository.save(user);
    }
}
