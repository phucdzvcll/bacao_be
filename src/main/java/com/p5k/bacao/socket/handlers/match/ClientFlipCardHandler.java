package com.p5k.bacao.socket.handlers.match;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.p5k.bacao.socket.core.enums.ListenEvent;
import com.p5k.bacao.socket.core.enums.SendEvent;
import com.p5k.bacao.socket.core.handler.BaseHandler;
import com.p5k.bacao.socket.dto.match.ClientFlipCardDto;
import com.p5k.bacao.socket.payload.match.ClientFlipCardPayload;
import org.springframework.stereotype.Service;

@Service
public class ClientFlipCardHandler extends BaseHandler<ClientFlipCardPayload> {

  protected ClientFlipCardHandler() {
    super(ListenEvent.CLIENT_FLIP_CARD);
  }

  @Override
  public void onData(SocketIOClient client, ClientFlipCardPayload clientFlipCardPayload,
      AckRequest ackRequest, String userId, BroadcastOperations roomBroadcast) {

    roomBroadcast.sendEvent(SendEvent.CLIENT_FLIP_CARD.getMessage(),
        ClientFlipCardDto
            .builder()
            .index(clientFlipCardPayload.getIndex())
            .userId(clientFlipCardPayload.getUserId())
            .build());

  }
}
