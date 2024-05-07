package com.example.boards.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
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
//    @CreatedBy
//    @Column(updatable = false)
//    private String createdBy;

    @LastModifiedDate
    private LocalDateTime localModifiedDate;

//    @LastModifiedBy
//    private String lastModifiedBy;
}
