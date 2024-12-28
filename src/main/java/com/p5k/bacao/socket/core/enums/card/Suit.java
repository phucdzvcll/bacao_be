package com.p5k.bacao.socket.core.enums.card;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Suit {
    HEARTS(4),   // Cơ
    DIAMONDS(3), // Rô
    CLUBS(2),    // Chuồn
    SPADES(1);   // Bích

    private final int strength;

    Suit(int strength) {
        this.strength = strength;
    }
    @JsonCreator
    public static Suit fromValue(String value) {
        for (Suit suit : values()) {
            if (suit.name().equalsIgnoreCase(value)) {
                return suit;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

}
