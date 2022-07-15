package com.lingxi.lingxi_java.common.filters;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.lingxi.lingxi_java.common.Constants;
import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.utils.AuthenticationUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
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
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 如果是白名单接口，就放开校验
        if (LOGIN_REQUEST_MATHER.stream().anyMatch(x -> x.matches(request))) {
            filterChain.doFilter(request, response);
            return;
        }
        // 解析jwt的userId，认证并查询用户信息到全局的manager
        try {
            AuthenticationUtil.setUserId(buildJWTAuthenticationToken(request));
        } catch (Throwable e) {
            log.info("认证失败", e);
            ResponseCode errorCode = ResponseCode.TOKEN_CHECK_ERROR;
            if (e instanceof TokenExpiredException) {
                errorCode = ResponseCode.ACCESS_EXPIRED_ERROR;
            }
            failureResponse(response, errorCode);
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
