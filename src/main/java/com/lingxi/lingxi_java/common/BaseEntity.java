package com.lingxi.lingxi_java.common;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @CreatedBy
    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long createdBy = 0L;
    @LastModifiedBy
    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long updatedBy = 0L;
    @CreatedDate
    @Column(nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date createdAt = new Date();
    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date updatedAt = new Date();
}
