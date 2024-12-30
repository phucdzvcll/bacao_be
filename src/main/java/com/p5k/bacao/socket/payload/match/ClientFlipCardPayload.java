package com.p5k.bacao.socket.payload.match;

import com.p5k.bacao.socket.core.payload.BasePayload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ClientFlipCardPayload extends BasePayload {

  @NotNull(message = "Exception.MustNotBeNull")
  private int index;

  @NotNull(message = "Exception.MustNotBeNull")
  @NotEmpty(message = "Exception.MustNotBeEmpty")
  private String userId;
}
