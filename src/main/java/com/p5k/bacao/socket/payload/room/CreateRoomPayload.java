package com.p5k.bacao.socket.payload.room;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateRoomPayload {
    private String roomId = UUID.randomUUID().toString();

    private String adminId;

    private String adminClientId;

    private String password;

    private List<UserInRoomPayload> userIds;

    @NotNull(message = "Exception.MustNotBeNull")
    @NotEmpty(message = "Exception.MustNotBeEmpty")
    private String roomName;
}
