package com.p5k.bacao.socket.core.enums.card;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum CombinationType {
  NORMAL(1),
  THREE_FACE(2),
  STRAIGHT(3),
  THREE_OF_KIND(4),
  ;

  private final int power;

  CombinationType(int power) {
    this.power = power;
  }

  @JsonCreator
  public static CombinationType fromValue(String value) {
    for (CombinationType CombinationType : values()) {
      if (CombinationType.name().equalsIgnoreCase(value)) {
        return CombinationType;
      }
    }
    throw new IllegalArgumentException("Unknown value: " + value);
  }
}
