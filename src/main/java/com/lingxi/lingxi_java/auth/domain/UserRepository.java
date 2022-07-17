package com.lingxi.lingxi_java.auth.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUsername(String username);

    @Query(value = "select u.* from user u join user_role_relation ur on ur.user_id = u.id " +
            "where ur.role_id = ?", nativeQuery = true)
    Page<User> findAllByRoleId(Pageable pageable, Long roleId);
}
