package com.p5k.bacao.socket.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateRoomPayload {
    private String id = UUID.randomUUID().toString();

    private String roomName;
}
