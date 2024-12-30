package com.p5k.bacao.socket.handlers.room;

import static com.p5k.bacao.socket.core.enums.SendEvent.CLIENT_READIED;
import static com.p5k.bacao.socket.core.enums.SendEvent.NEW_GAME;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.socket.core.enums.ListenEvent;
import com.p5k.bacao.socket.core.enums.UserStateEnum;
import com.p5k.bacao.socket.core.handler.BaseHandler;
import com.p5k.bacao.socket.dto.match.MatchDto;
import com.p5k.bacao.socket.dto.room.ClientReadiedDto;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.dto.room.UserInRoomDto;
import com.p5k.bacao.socket.payload.room.ClientReadyPayload;
import com.p5k.bacao.socket.service.match.MatchService;
import com.p5k.bacao.socket.service.room.RoomService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClientReadyHandler extends BaseHandler<ClientReadyPayload> {

  protected ClientReadyHandler(RoomService roomService, MatchService matchService) {
    super(ListenEvent.CLIENT_READY);
    this.roomService = roomService;
    this.matchService = matchService;
  }

  final RoomService roomService;
  final MatchService matchService;

  @Override
  public void onData(SocketIOClient client, ClientReadyPayload clientReadyPayload,
      AckRequest ackRequest, String userId, BroadcastOperations roomBroadcast) {
    RoomDto roomDto = roomService.clientReady(clientReadyPayload.getRoomId(), userId);
    ClientReadiedDto clientReadiedDto = new ClientReadiedDto();
    clientReadiedDto.setUserId(userId);
    roomBroadcast.sendEvent(CLIENT_READIED.getMessage(), clientReadiedDto);

    List<UserInRoomDto> usersNotReadied = roomDto.getUserIds().stream()
        .filter(id -> id.getUserStateEnum() != UserStateEnum.READIED).toList();

    if (XChecker.isEmpty(usersNotReadied)
//        && roomDto.getUserIds().size() > 1
    ) {
      MatchDto matchDto = matchService.createMatch(roomDto);
      roomBroadcast.sendEvent(NEW_GAME.getMessage(), matchDto);
    }
  }


}
