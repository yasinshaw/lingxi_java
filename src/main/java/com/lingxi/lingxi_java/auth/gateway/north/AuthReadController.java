package com.lingxi.lingxi_java.auth.gateway.north;

import com.lingxi.lingxi_java.auth.application.*;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/")
public class AuthReadController {

    @Resource
    private AuthQueryService authQueryService;

    @GetMapping("/currentUserInfo")
    public UserInfoResponse getCurrentUserInfo() {
        return authQueryService.getCurrentUserInfo();
    }

    @GetMapping("/permissions")
    public Page<PermissionInfoResponse> getPermissionList(Pageable pageable) {
        return authQueryService.getPermissionList(pageable);
    }

    @GetMapping("/roles")
    public Page<RoleInfoResponse> getRoleList(Pageable pageable) {
        return authQueryService.getRoleList(pageable);
    }

    @GetMapping("/rolesByPermission")
    public Page<RoleInfoResponse> getRoleListByPermissionId(Pageable pageable, Long permissionId) {
        return authQueryService.getRoleListByPermissionId(pageable, permissionId);
    }

    @GetMapping("/rolesByUser")
    public Page<RoleInfoResponse> getRoleListByUserId(Pageable pageable, Long userId) {
        return authQueryService.getRoleListByUserId(pageable, userId);
    }

    @GetMapping("/users")
    public Page<UserListResponse> getUserList(Pageable pageable) {
        return authQueryService.getUserList(pageable);
    }

    @GetMapping("/usersByRole")
    public Page<UserListResponse> getUserListByRoleId(Pageable pageable, Long roleId) {
        return authQueryService.getUserListByRoleId(pageable, roleId);
    }

    @GetMapping("/permissionsByRole")
    public Page<PermissionInfoResponse> getPermissionListByRoleId(Pageable pageable, Long roleId) {
        return authQueryService.getPermissionListByRoleId(pageable, roleId);
    }
}
