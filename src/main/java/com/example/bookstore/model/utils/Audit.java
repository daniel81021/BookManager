package com.example.bookstore.model.utils;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class Audit {


    @Column(name = "created_on", updatable = false)
    private LocalDate createDate;

    @Column(name = "updated_on")
    private LocalDate updateDate;

    @PrePersist
    public void prePersist() {
        createDate = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDate.now();
    }
}
