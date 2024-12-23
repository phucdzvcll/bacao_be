package com.p5k.bacao.socket.core.enums;

import lombok.Getter;

@Getter
public enum ListenEvent {

    CREATE_ROOM("createRoom"),
    JOIN_TO_ROOM("joinToRoom"),
    JOIN_TO_LOBBY("joinToLobby"),
    CHANGE_SEAT("changeSeat"),
    LEAVE_ROOM("leaveRoom");

    private final String message;

    ListenEvent(String message) {
        this.message = message;
    }


}
