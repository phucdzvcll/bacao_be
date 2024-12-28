package com.p5k.bacao.socket.payload.room;

import com.p5k.bacao.socket.core.payload.BasePayload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeSeatPayload extends BasePayload {

    @NotNull(message = "Exception.MustNotBeNull")
    @NotEmpty(message = "Exception.MustNotBeEmpty")
    private int seatNum;
}
