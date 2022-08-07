package com.lingxi.lingxi_java.utils;

import com.lingxi.lingxi_java.common.dto.MyAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class AuthenticationUtil {
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return 0L;
        }
        if (authentication.getPrincipal() instanceof Long) {
            return (Long) authentication.getPrincipal();
        }
        log.warn("获取用户信息失败，类型转换失败!");
        return 0L;
    }

    public static void setUserId(String token, Long userId) {
        SecurityContextHolder.getContext().setAuthentication(new MyAuthenticationToken(
                token, userId
        ));
    }
}
