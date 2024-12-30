package com.p5k.bacao.socket.core.enums;

import lombok.Getter;

@Getter
public enum SendEvent {

    CREATE_ROOM_SUCCESS("createRoomSuccess"),
    JOIN_TO_ROOM_SUCCESS("joinToRoomSuccess"),
    NEW_CLIENT_JOIN_TO_ROOM("newClientJoinToRoom"),
    UPDATE_LOBBY("updateLobby"),
    CLIENT_LEAVED_ROOM("clientLeavedRoom"),
    CLIENT_CHANGE_SEAT("clientChangeSeat"),
    JOIN_TO_ROOM_LOBBY_SUCCESS("joinToLobbySuccess"),
    CLIENT_READIED("clientReadied"),
    CLIENT_FLIP_CARD("clientFlipCard"),
    CLIENT_FLIP_ALL_CARD("clientFlipAllCard"),
    NEW_GAME("newGame"),

    //error
    EX_ERROR("exError");

    private final String message;

    SendEvent(String message) {
        this.message = message;
    }


}
