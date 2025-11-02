package org.fintech.paytech.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseDeletableModel extends BaseModel {

    @Column(name = "deleted")
    private LocalDateTime deleted;

    private Boolean isDeleted = false;
}
