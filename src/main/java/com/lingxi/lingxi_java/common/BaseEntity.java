package com.lingxi.lingxi_java.common;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
