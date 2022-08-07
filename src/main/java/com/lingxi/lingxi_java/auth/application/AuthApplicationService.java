package com.lingxi.lingxi_java.auth.application;

import com.lingxi.lingxi_java.auth.domain.*;
import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.common.enums.PermissionTypeEnum;
import com.lingxi.lingxi_java.common.exceptions.BizException;
import com.lingxi.lingxi_java.common.exceptions.NoPermissionException;
import com.lingxi.lingxi_java.utils.AuthenticationUtil;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public void updateApiPermissions(Set<String> newPermissions) {
        HashSet<Permission> dbPermissions = new HashSet<>(permissionRepository.findAllByType(PermissionTypeEnum.API));
        Set<String> dbPermissionValues = dbPermissions.stream().map(Permission::getValue).collect(Collectors.toSet());
        dbPermissions.forEach(v -> {
            if (!newPermissions.contains(v.getValue())) {
                permissionRepository.delete(v);
            }
        });
        newPermissions.forEach(v -> {
            if (!dbPermissionValues.contains(v)) {
                Permission permission = Permission.create(PermissionTypeEnum.API, v);
                permissionRepository.save(permission);
            }
        });
    }

    @Transactional
    public void updateMenuPermissions(Set<String> newPermissions) {
        HashSet<Permission> dbPermissions = new HashSet<>(permissionRepository.findAllByType(PermissionTypeEnum.MENU));
        Set<String> dbPermissionValues = dbPermissions.stream().map(Permission::getValue).collect(Collectors.toSet());
        dbPermissions.forEach(v -> {
            if (!newPermissions.contains(v.getValue())) {
                permissionRepository.delete(v);
            }
        });
        newPermissions.forEach(v -> {
            if (!dbPermissionValues.contains(v)) {
                Permission permission = Permission.create(PermissionTypeEnum.MENU, v);
                permissionRepository.save(permission);
            }
        });
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

    @Transactional
    public void createUser(CreateUserRequest request) {
        Optional<User> existedUser = userRepository.findOneByUsername(request.getUsername());
        if (existedUser.isPresent()) {
            throw new BizException(ResponseCode.REPEAT_USERNAME);
        }
        User user = User.create(request.getUsername(), request.getPassword(), request.getAvatar(), request.getNickName());
        userRepository.save(user);
    }

    @Transactional
    public void updatePermissionRoleRelation(UpdatePermissionRoleRelationRequest request) {
        Permission permission = permissionRepository.findById(request.getPermissionId()).orElseThrow(() -> new BizException("查找不到权限"));
        List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        permission.updateRoles(new HashSet<>(roles));
        permissionRepository.save(permission);
    }

    @Transactional
    public void updateRolePermissionRelation(UpdateRolePermissionRelationRequest request) {
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(() -> new BizException("查找不到角色"));
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissionIds());
        role.updatePermissions(new HashSet<>(permissions));
        roleRepository.save(role);
    }

    @Transactional
    public void updateRoleUserRelation(UpdateRoleUserRelationRequest request) {
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(() -> new BizException("查找不到角色"));
        List<User> users = userRepository.findAllById(request.getUserIds());
        role.updateUsers(new HashSet<>(users));
        roleRepository.save(role);

    }

    @Transactional
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new BizException("查找不到角色"));
        roleRepository.delete(role);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BizException(ResponseCode.NO_USER));
        userRepository.delete(user);
    }

    @Transactional
    public void updateUser(UpdateUserRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new BizException(ResponseCode.NO_USER));
        user.updateUserInfo(request.getNickName(), request.getAvatar());
        userRepository.save(user);
    }

    @Transactional
    public void checkApiPermission(String requestURI) throws NoPermissionException {
        Long currentUserId = AuthenticationUtil.getCurrentUserId();
        User user = userRepository.findById(currentUserId).orElseThrow(() -> new BizException(ResponseCode.NO_USER));
        user.checkApiPermission(requestURI);
    }
}
