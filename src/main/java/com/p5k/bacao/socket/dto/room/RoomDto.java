package com.p5k.bacao.socket.dto.room;

import com.p5k.bacao.socket.core.enums.RoomStatus;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RoomDto {

    private String roomId = UUID.randomUUID().toString().replace("-", "");

    private String adminId;

    private String adminClientId;

    private String password;

    private String roomName;

    private RoomStatus roomStatus;

    private List<UserInRoomDto> userIds;
}
