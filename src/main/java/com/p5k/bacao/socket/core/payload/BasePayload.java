package com.p5k.bacao.socket.core.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasePayload implements Serializable {

    @NotNull(message = "Exception.MustNotBeNull")
    @NotEmpty(message = "Exception.MustNotBeEmpty")
    private String roomName;

    @NotNull(message = "Exception.MustNotBeNull")
    @NotEmpty(message = "Exception.MustNotBeEmpty")
    private String roomId;

}
