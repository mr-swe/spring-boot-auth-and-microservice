package com.example.family_transaction.application.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

/**
 * @author Mahfuzur Rahman
 * @Date 8/13/2024
 */

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String uuid;

    @NotNull
    private Boolean isDeleted;

    @NotNull
    private String createdBy;

    @NotNull
    private LocalDateTime createdDate;

    private String modifyBy;

    private LocalDateTime modifiedDate;

    @PrePersist
    private void prePersist() {
        this.setCreatedDate(LocalDateTime.now());
        this.setIsDeleted(false);
    }

    @PreUpdate
    private void preUpdate() {
        this.setModifiedDate(LocalDateTime.now());
    }

}
