package com.p5k.bacao.socket.payload.room;

import com.p5k.bacao.socket.core.payload.BasePayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientReadyPayload extends BasePayload {
}