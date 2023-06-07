package com.example.bookstore.model.enums;

import lombok.Getter;

@Getter
public enum State {

    NEW("New"),
    USED("Used"),
    BROKEN("Broken");

    public final String label;

    State(String label) {
        this.label = label;
    }
}
