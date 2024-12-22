package com.p5k.bacao.socket.dto.room;

import lombok.Data;

@Data
public class UserInRoomDto {
    private String userId;
    private String skSessionId;
    private int seatNum;
}
