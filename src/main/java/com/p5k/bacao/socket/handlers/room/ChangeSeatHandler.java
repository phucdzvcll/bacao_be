package com.p5k.bacao.socket.handlers.room;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.socket.core.handler.BaseHandler;
import com.p5k.bacao.socket.dto.room.ChangeSeatSuccessDto;
import com.p5k.bacao.socket.payload.room.ChangeSeatPayload;
import com.p5k.bacao.socket.service.room.RoomService;
import org.springframework.stereotype.Service;

import static com.p5k.bacao.socket.core.enums.ListenEvent.CHANGE_SEAT;
import static com.p5k.bacao.socket.core.enums.SendEvent.CLIENT_CHANGE_SEAT;

@Service
public class ChangeSeatHandler extends BaseHandler<ChangeSeatPayload> {

    protected ChangeSeatHandler(RoomService roomService) {
        super(CHANGE_SEAT);
        this.roomService = roomService;
    }

    final private RoomService roomService;

    @Override
    public void onData(SocketIOClient client, ChangeSeatPayload changeSeatPayload, AckRequest ackRequest, String userId, BroadcastOperations roomBroadcast) {
        roomService.changeSeatService(changeSeatPayload.getRoomId(), userId, changeSeatPayload.getSeatNum());
        ChangeSeatSuccessDto changeSeatSuccessDto = ChangeSeatSuccessDto
                .builder()
                .newSeat(changeSeatPayload.getSeatNum())
                .userId(userId)
                .build();

        roomBroadcast
                .sendEvent(CLIENT_CHANGE_SEAT.getMessage(), changeSeatSuccessDto);
    }
}
