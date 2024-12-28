package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.socket.core.enums.ListenEvent;
import com.p5k.bacao.socket.core.handler.BaseHandler;
import com.p5k.bacao.socket.dto.room.ClientReadiedDto;
import com.p5k.bacao.socket.payload.room.ClientReadyPayload;
import com.p5k.bacao.socket.service.room.RoomService;
import org.springframework.stereotype.Service;

import static com.p5k.bacao.socket.core.enums.SendEvent.CLIENT_READIED;

@Service
public class ClientReadyHandler extends BaseHandler<ClientReadyPayload> {
    protected ClientReadyHandler(RoomService roomService) {
        super(ListenEvent.CLIENT_READY);
        this.roomService = roomService;
    }

    final RoomService roomService;

    @Override
    public void onData(SocketIOClient client, ClientReadyPayload clientReadyPayload, AckRequest ackRequest, String userId, BroadcastOperations roomBroadcast) {
        ClientReadiedDto clientReadiedDto = roomService.clientReady(clientReadyPayload.getRoomId(), userId);
        roomBroadcast.sendEvent(CLIENT_READIED.getMessage(), clientReadiedDto);
    }
}
