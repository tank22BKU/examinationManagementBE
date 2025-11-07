package com.examManagementBE.entity;

import com.examManagementBE.utils.DateTimeUtils;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;


@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseTimeEntity {
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updateAt;

    @PrePersist
    public void onCreate() {
        this.setCreateAt(DateTimeUtils.getDateTimeNow());
        this.setUpdateAt(DateTimeUtils.getDateTimeNow());
    }

    @PreUpdate
    public void onUpdate() {
        this.setUpdateAt(DateTimeUtils.getDateTimeNow());
    }
}

