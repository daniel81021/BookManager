package com.example.bookstore.publisher.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {
    private Long id;
    private String name;
    private Long version;
}
