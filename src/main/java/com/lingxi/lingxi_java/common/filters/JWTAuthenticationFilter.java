package com.lingxi.lingxi_java.common.filters;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.lingxi.lingxi_java.auth.application.AuthApplicationService;
import com.lingxi.lingxi_java.common.Constants;
import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.common.exceptions.NoPermissionException;
import com.lingxi.lingxi_java.utils.AuthenticationUtil;
import com.lingxi.lingxi_java.utils.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.lingxi.lingxi_java.common.Constants.AUTHORIZATION;
import static com.lingxi.lingxi_java.common.Constants.AUTH_PREFIX;
import static com.lingxi.lingxi_java.utils.HttpResponseUtil.failureResponse;

@Component
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private static final List<RequestMatcher> LOGIN_REQUEST_MATHER = Arrays.stream(Constants.AUTH_WHITELIST)
            .map(AntPathRequestMatcher::new).collect(Collectors.toList());
    @Resource
    private AuthApplicationService authApplicationService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 如果是白名单接口，就放开校验
        if (LOGIN_REQUEST_MATHER.stream().anyMatch(x -> x.matches(request))) {
            // 如果有token，尝试解析并赋予登录状态，但不阻碍接口调用
            try {
                String token = buildJWTAuthenticationToken(request);
                AuthenticationUtil.setUserId(token, JwtUtil.getUserIdByToken(token));
            } catch (Throwable e) {
                log.warn("设置登录态失败");
            }
            filterChain.doFilter(request, response);
            return;
        }
        // 解析jwt的userId，认证并查询用户信息到全局的manager
        try {
            String token = buildJWTAuthenticationToken(request);
            AuthenticationUtil.setUserId(token, JwtUtil.getUserIdByToken(token));
            authApplicationService.checkApiPermission(request.getMethod() + " " + request.getRequestURI());
        } catch (NoPermissionException e) {
            log.warn("鉴权失败", e);
            failureResponse(response, ResponseCode.NO_PERMISSION);
            SecurityContextHolder.clearContext();
            return;
        } catch (TokenExpiredException e) {
            log.info("认证失败", e);
            failureResponse(response, ResponseCode.ACCESS_EXPIRED_ERROR);
            SecurityContextHolder.clearContext();
            return;
        } catch (Throwable e) {
            log.info("认证失败", e);
            failureResponse(response, ResponseCode.TOKEN_CHECK_ERROR);
            SecurityContextHolder.clearContext();
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String buildJWTAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return token.replace(AUTH_PREFIX, "");
    }
}
