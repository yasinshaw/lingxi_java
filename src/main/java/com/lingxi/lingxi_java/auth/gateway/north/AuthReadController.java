package com.lingxi.lingxi_java.auth.gateway.north;

import com.lingxi.lingxi_java.auth.application.AuthQueryService;
import com.lingxi.lingxi_java.auth.application.PermissionInfoResponse;
import com.lingxi.lingxi_java.auth.application.UserInfoResponse;
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
}
