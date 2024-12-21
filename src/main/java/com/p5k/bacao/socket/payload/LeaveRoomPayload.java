package com.p5k.bacao.socket.payload;

import lombok.Data;

@Data
public class LeaveRoomPayload {

    private String roomId;
    private String roomName;

}
