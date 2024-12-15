package com.p5k.bacao.socket.payload;

import lombok.Data;

@Data
public class UserInRoomPayload {
    private String userId;
    private String skSessionId;
}
