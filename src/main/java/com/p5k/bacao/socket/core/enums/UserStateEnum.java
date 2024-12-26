package com.p5k.bacao.socket.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserStateEnum {
    WAITING("WAITING"),
    READIED("READIED"),
    PLAYING("PLAYING"),
    VICTORY("VICTORY"),
    ;

    private final String state;

    UserStateEnum(String state) {
        this.state = state;
    }

    @JsonCreator
    public static UserStateEnum fromValue(String value) {
        for (UserStateEnum state : values()) {
            if (state.name().equalsIgnoreCase(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

}
