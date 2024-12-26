package com.p5k.bacao.socket.dto.room;

import com.p5k.bacao.socket.core.enums.UserStateEnum;
import lombok.Data;

@Data
public class UserInRoomDto {
    private String userId;
    private String skSessionId;
    private UserStateEnum userStateEnum = UserStateEnum.WAITING;
    private int seatNum;
}
