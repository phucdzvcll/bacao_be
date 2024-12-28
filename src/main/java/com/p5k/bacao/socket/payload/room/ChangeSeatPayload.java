package com.p5k.bacao.socket.payload.room;

import com.p5k.bacao.socket.core.payload.BasePayload;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeSeatPayload extends BasePayload {

    @NotNull(message = "Exception.MustNotBeNull")
    @DecimalMin(value = "0", message = "Exception.MustBeGreaterThanZero")
    private int seatNum;
}
