package com.example.boards.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //BaseEntity를 상속한 엔티티들은 아래 필드들을컬럼으로 인식하게 된다
@EntityListeners(AuditingEntityListener.class) //Auditing(자동 값 매핑 추가)
public abstract class BaseEntity {
    @CreatedDate
    LocalDateTime createTime;
    @PrePersist
    public void prePersist() {
        this.createTime = LocalDateTime.now();
    }
//    @CreatedBy
//    @Column(updatable = false)
//    private String createdBy;

    @LastModifiedDate
    private LocalDateTime localModifiedDate;

    @PreUpdate
    public void preUpdate() {
        this.localModifiedDate = LocalDateTime.now();
    }
//    @LastModifiedBy
//    private String lastModifiedBy;

    boolean isDeleted;

}
