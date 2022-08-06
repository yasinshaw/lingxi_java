package com.lingxi.lingxi_java.auth.gateway.north;

import com.lingxi.lingxi_java.auth.application.*;
import com.lingxi.lingxi_java.common.Constants;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/admin/")
public class AuthWriteController {

    @Resource
    private AuthApplicationService authApplicationService;

    @Resource
    private HttpServletResponse httpServletResponse;

    @Resource
    private WebApplicationContext webApplicationContext;
    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @PostMapping("updateApiPermissions")
    public void updateApiPermissions() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        Set<String> permissions = handlerMethods.keySet().stream().map((v) -> {
            Set<RequestMethod> requestMethods = v.getMethodsCondition().getMethods();
            if (CollectionUtils.isEmpty(requestMethods)) {
                return "";
            }
            PatternsRequestCondition patternsCondition = v.getPatternsCondition();
            if (patternsCondition == null) {
                return "";
            }
            return requestMethods.stream().toList().get(0).name() + " " + patternsCondition.getPatterns().stream().toList().get(0);
        }).filter(StringUtils::isNoneBlank).collect(Collectors.toSet());
        authApplicationService.updateApiPermissions(permissions);
    }

    @PostMapping("updateManuPermissions")
    public void updateManuPermissions(@RequestBody Set<String> manuPermissions) {
        authApplicationService.updateMenuPermissions(manuPermissions);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Validated LoginRequest request) {
        String token = authApplicationService.login(request);
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

    @PostMapping("/user/updatePermissionRoleRelation")
    public void updatePermissionRoleRelation(@RequestBody @Validated UpdatePermissionRoleRelationRequest request) {
        authApplicationService.updatePermissionRoleRelation(request);
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
