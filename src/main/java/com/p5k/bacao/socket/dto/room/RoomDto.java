package com.p5k.bacao.socket.dto.room;

import java.util.List;
import lombok.Data;

@Data
public class RoomDto {

    private String roomId;

    private String adminId;

    private String adminClientId;

    private String password;

    private String roomName;

    private List<UserInRoomDto> userIds;
}
