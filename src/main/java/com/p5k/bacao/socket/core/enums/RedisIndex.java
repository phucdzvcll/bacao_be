package com.p5k.bacao.socket.core.enums;


import lombok.Getter;

@Getter
public enum RedisIndex {
  ROOM_BY_NAME("room_idx"),
  ;

  private final String index;

  RedisIndex(String message) {
    this.index = message;
  }

}
