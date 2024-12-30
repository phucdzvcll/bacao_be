package com.p5k.bacao.socket.dto.match;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientFlipCardDto {

  private String userId;

  private int index;
}
