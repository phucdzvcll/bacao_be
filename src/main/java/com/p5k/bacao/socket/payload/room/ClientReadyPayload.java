package com.p5k.bacao.socket.payload.room;

import lombok.Data;

@Data
public class ClientReadyPayload {
    private String roomId;
    private String roomName;
}
