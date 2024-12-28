package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.socket.core.enums.SendEvent;
import com.p5k.bacao.socket.core.handler.BaseHandler;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.dto.room.UserInRoomDto;
import com.p5k.bacao.socket.payload.room.JoinRoomPayload;
import com.p5k.bacao.socket.service.room.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.p5k.bacao.socket.core.enums.ListenEvent.JOIN_TO_ROOM;

@Service
public class JoinToRoomHandler extends BaseHandler<JoinRoomPayload> {

    private final RoomService roomService;

    protected JoinToRoomHandler(RoomService roomService) {
        super(JOIN_TO_ROOM);
        this.roomService = roomService;
    }


    @Override
    public void onData(SocketIOClient client, @Validated JoinRoomPayload joinRoomPayload, AckRequest ackRequest, String userId, BroadcastOperations roomBroadcast) {
        RoomDto roomDto = roomService.findRoomById(joinRoomPayload.getRoomId());
        XChecker.isTrueThruMsg(roomDto == null, "Room not found");
        roomService.joinToRoom(joinRoomPayload.getRoomId(), userId, client.getSessionId().toString().replace("-", "")).thenRunAsync(() -> {
            RoomDto newRoom = roomService.findRoomById(joinRoomPayload.getRoomId());
            client.sendEvent(SendEvent.JOIN_TO_ROOM_SUCCESS.getMessage(), newRoom);
            client.getNamespace().getBroadcastOperations().sendEvent(SendEvent.UPDATE_LOBBY.getMessage(), newRoom);
            UserInRoomDto userInRoomDto = newRoom.getUserIds()
                    .stream()
                    .filter(userInRoomDto1 ->
                            userInRoomDto1
                                    .getUserId()
                                    .equals(userId))
                    .findFirst()
                    .orElse(null);
            roomBroadcast.sendEvent(SendEvent.NEW_CLIENT_JOIN_TO_ROOM.getMessage(), userInRoomDto);
            client.joinRoom(newRoom.getRoomName());
        });

    }
}
