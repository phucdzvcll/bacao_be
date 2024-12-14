package com.p5k.bacao.socket.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomDto {
    private String roomId;

    private String adminId;

    private String password;

    private String roomName;

    private List<String> userIds;
}
