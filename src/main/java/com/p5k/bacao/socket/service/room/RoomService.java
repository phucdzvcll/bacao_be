package com.p5k.bacao.socket.service.room;

import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.payload.room.CreateRoomPayload;

import java.util.List;

public abstract class RoomService {
    public abstract List<RoomDto> getAllRooms();

    public abstract RoomDto createRoom(CreateRoomPayload createRoomPayload, String userId, String clientId);

    public abstract RoomDto findRoomById(String roomId);

    public abstract RoomDto joinToRoom(String roomId, String userId, String clientId);
}
