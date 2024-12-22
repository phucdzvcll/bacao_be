package com.p5k.bacao.socket.payload.room;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JoinRoomPayload {
    @NotNull(message = "Exception.MustNotBeNull")
    @NotEmpty(message = "Exception.MustNotBeEmpty")
    private String roomId;
}
