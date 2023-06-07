package com.example.bookstore.model.utils;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class CreateUpdateData {

    private LocalDate createDate;

    private LocalDate updateDate;

//    @PrePersist
//    private void prePersist(){
//        this.createDate = LocalDate.now();
//        this.updateDate = this.createDate;
//    }
//
//    @PreUpdate
//    private void preUpdate(){
//        this.updateDate = LocalDate.now();
//    }
}
