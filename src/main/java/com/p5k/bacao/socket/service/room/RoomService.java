package com.p5k.bacao.socket.service.room;

import com.p5k.bacao.socket.dto.room.ClientReadiedDto;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.payload.room.CreateRoomPayload;

import java.util.List;
import java.util.concurrent.CompletionStage;

public abstract class RoomService {
    public abstract List<Object> getAllRooms();

    public abstract RoomDto createRoom(CreateRoomPayload createRoomPayload, String userId, String clientId);

    public abstract RoomDto findRoomById(String roomId);

    public abstract RoomDto findRoomName(String roomName);

    public abstract CompletionStage<Long> joinToRoom(String roomId, String userId, String clientId);

    public abstract RoomDto leaveRoom(String roomId, String userId, String clientId);

    public abstract RoomDto changeSeatService(String roomId, String userId, int seatNum);

    public abstract ClientReadiedDto clientReady(String roomId, String userId);
}
