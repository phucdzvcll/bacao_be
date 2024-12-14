package com.p5k.bacao.socket.payload;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateRoomPayload {
    private String roomId = UUID.randomUUID().toString();

    private String adminId;

    private String password;

    private List<String> userIds;

    private String roomName;
}
