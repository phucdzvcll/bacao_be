package com.p5k.bacao.socket.core.enums.card;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Rank {
    A(1, 14),     // Át, mạnh nhất
    TWO(2, 2),    // 2 điểm, sức mạnh yếu
    THREE(3, 3),
    FOUR(4, 4),
    FIVE(5, 5),
    SIX(6, 6),
    SEVEN(7, 7),
    EIGHT(8, 8),
    NINE(9, 9),
    TEN(10, 10),
    J(10, 11),    // Tây J, 10 điểm, sức mạnh 11
    Q(10, 12),    // Tây Q, 10 điểm, sức mạnh 12
    K(10, 13);    // Tây K, 10 điểm, sức mạnh 13

    private final int points; // Điểm số của bài
    private final int power;  // Sức mạnh của bài

    Rank(int points, int power) {
        this.points = points;
        this.power = power;
    }

    String getDpName() {
        return switch (this) {
            case A -> "A";
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            case NINE -> "9";
            case TEN -> "10";
            case J -> "J";
            case Q -> "Q";
            case K -> "K";
        };
    }

    @JsonCreator
    public static Rank fromValue(String value) {
        for (Rank rank : values()) {
            if (rank.name().equalsIgnoreCase(value)) {
                return rank;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
