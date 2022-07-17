package com.lingxi.lingxi_java.auth.gateway.north;

import com.lingxi.lingxi_java.auth.application.*;
import com.lingxi.lingxi_java.common.Constants;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/")
public class AuthWriteController {

    @Resource
    private AuthApplicationService authApplicationService;

    @Resource
    private HttpServletResponse httpServletResponse;

    @PostMapping("/login")
    public void login(@RequestBody @Validated LoginRequest request) {
        String token =  authApplicationService.login(request);
        httpServletResponse.addHeader(Constants.AUTHORIZATION, Constants.AUTH_PREFIX + token);
    }


    @PostMapping("/user/updateCurrentUserInfo")
    public void updateCurrentUserInfo(@RequestBody @Validated UpdateCurrentUserInfoRequest request) {
        authApplicationService.updateCurrentUserInfo(request);
    }

    @PostMapping("/user/changePassword")
    public void changePassword(@RequestBody @Validated ChangePasswordRequest request) {
        authApplicationService.changePassword(request);
    }

    @PostMapping("/user/create")
    public void createUser(@RequestBody @Validated CreateUserRequest request) {
        authApplicationService.createUser(request);
    }

    @PostMapping("/user/updateUserRoleRelation")
    public void updateUserRoleRelation(@RequestBody @Validated UpdateUserRoleRelationRequest request) {
        authApplicationService.updateUserRoleRelation(request);
    }

    @PostMapping("/role/create")
    public Long createRole(@RequestBody @Validated CreateRoleRequest request) {
        return authApplicationService.createRole(request);
    }

    @PostMapping("/role/update")
    public void updateRole(@RequestBody @Validated UpdateRoleRequest request) {
        authApplicationService.updateRole(request);
    }

    @PostMapping("/permission/create")
    public Long createPermission(@RequestBody @Validated CreatePermissionRequest request) {
        return authApplicationService.createPermission(request);
    }

    @PostMapping("/permission/update")
    public void updatePermission(@RequestBody @Validated UpdatePermissionRequest request) {
        authApplicationService.updatePermission(request);
    }

}
