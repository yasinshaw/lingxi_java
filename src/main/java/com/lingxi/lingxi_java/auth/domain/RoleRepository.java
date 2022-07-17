package com.lingxi.lingxi_java.auth.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r.* from role r join role_permission_relation rp on rp.role_id = r.id " +
            "where rp.permission_id = ?", nativeQuery = true)
    Page<Role> findAllByPermissionId(Pageable pageable, Long permissionId);

    @Query(value = "select r.* from role r join user_role_relation ur on ur.role_id = r.id " +
            "where ur.user_id = ?", nativeQuery = true)
    Page<Role> findAllByUserId(Pageable pageable, Long userId);
}
