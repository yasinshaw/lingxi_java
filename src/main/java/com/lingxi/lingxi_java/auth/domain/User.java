package com.lingxi.lingxi_java.auth.domain;

import com.lingxi.lingxi_java.common.BaseEntity;
import com.lingxi.lingxi_java.common.ResponseCode;
import com.lingxi.lingxi_java.common.exceptions.BizException;
import com.lingxi.lingxi_java.utils.EncryptUtil;
import com.lingxi.lingxi_java.utils.JwtUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity(name = "user")
public class User extends BaseEntity {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String avatar;
    private String nickName;
    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "user_role_relation",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

    public static User create(String username, String password, String avatar, String nickName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(EncryptUtil.md5(password));
        user.setAvatar(avatar);
        user.setNickName(nickName);
        // todo yasin 这里给初始权限和角色
        return user;
    }

    public String login(String reqPassword) {
        if (!EncryptUtil.md5(reqPassword).equals(this.getPassword())) {
            throw new BizException(ResponseCode.LOGIN_FAILED);
        }
        return JwtUtil.generateByUserId(this.getId());
    }

    public void updateUserInfo(String nickName, String avatar) {
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public void updateUserRoleRelation(Set<Role> newRoles) {
        this.roles = newRoles;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!EncryptUtil.md5(oldPassword).equals(this.getPassword())) {
            throw new BizException("旧密码不正确，请重新输入！");
        }
        this.setPassword(EncryptUtil.md5(newPassword));
    }
}
