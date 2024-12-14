package com.p5k.bacao.socket.service.room;

import com.p5k.bacao.socket.dto.RoomDto;
import com.p5k.bacao.socket.payload.CreateRoomPayload;

import java.util.List;

public abstract class RoomService {
    public abstract List<RoomDto> getAllRooms();

    public abstract RoomDto createRoom(CreateRoomPayload createRoomPayload, String userId);
}
