package com.p5k.bacao.socket.dto.room;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeSeatSuccessDto {
    private final String userId;
    private final int newSeat;
    private final int oldSeat;
}
