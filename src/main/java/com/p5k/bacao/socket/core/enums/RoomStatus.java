package com.p5k.bacao.socket.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum RoomStatus {
    RUNNING("RUNNING"),
    WAITING("WAITING"),
    ENDING("ENDING"),
    ;

    private final String status;

    RoomStatus(String status) {
        this.status = status;
    }

    @JsonCreator
    public static RoomStatus fromValue(String value) {
        for (RoomStatus suit : values()) {
            if (suit.name().equalsIgnoreCase(value)) {
                return suit;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
