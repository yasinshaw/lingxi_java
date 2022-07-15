package com.lingxi.lingxi_java.auth.gateway.north;

import com.lingxi.lingxi_java.auth.application.AuthQueryService;
import com.lingxi.lingxi_java.auth.application.PermissionInfoResponse;
import com.lingxi.lingxi_java.auth.application.UserInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "权限管理", description = "权限查询相关接口")
@RequestMapping("/admin/")
public class AuthReadController {

    @Resource
    private AuthQueryService authQueryService;

    @GetMapping("/currentUserInfo")
    public UserInfoResponse getCurrentUserInfo() {
        return authQueryService.getCurrentUserInfo();
    }

    @GetMapping("/permissions")
    @Operation(summary = "权限列表", description = "分页查询权限")
    public Page<PermissionInfoResponse> getPermissionList(Pageable pageable) {
        return authQueryService.getPermissionList(pageable);
    }

    @GetMapping("/test")
    public void test() {
        ResponseEntity res = new ResponseEntity<>(new Object(), HttpStatus.OK);
        System.out.println(res);
        return;
    }

}
