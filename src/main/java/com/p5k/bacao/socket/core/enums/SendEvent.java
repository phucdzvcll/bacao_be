package com.p5k.bacao.socket.core.enums;

import lombok.Getter;

@Getter
public enum SendEvent {

    CREATE_ROOM_SUCCESS("createRoomSuccess"),
    JOIN_TO_ROOM_SUCCESS("joinToRoomSuccess"),
    JOIN_TO_ROOM_LOBBY_SUCCESS("joinToLobbySuccess"),
    NEW_ROOM_CREATED("newRoomCreated"),

    //error
    EX_ERROR("exError");

    private final String message;

    SendEvent(String message) {
        this.message = message;
    }


}
