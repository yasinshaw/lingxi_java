package com.lingxi.lingxi_java.auth.application;

import com.lingxi.lingxi_java.auth.AuthMapper;
import com.lingxi.lingxi_java.auth.domain.*;
import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.common.exceptions.BizException;
import com.lingxi.lingxi_java.utils.AuthenticationUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthQueryService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PermissionRepository permissionRepository;

    public UserInfoResponse getCurrentUserInfo() {
        Long userId = AuthenticationUtil.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new BizException(ResponseCode.NO_USER));
        Set<Role> roles = user.getRoles();
        List<Permission> permissions = permissionRepository.findAllByUserId(userId);
        AuthMapper mapper = AuthMapper.mapper;
        UserInfoResponse userInfoResponse = mapper.userEntity2Response(user);
        userInfoResponse.setRoles(roles.stream().map(mapper::roleEntity2Response).collect(Collectors.toList()));
        userInfoResponse.setPermissions(permissions.stream().map(mapper::permissionEntity2Response).collect(Collectors.toList()));
        return userInfoResponse;
    }
}
