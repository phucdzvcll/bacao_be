package com.p5k.bacao.socket.payload.room;

import com.p5k.bacao.socket.core.payload.BasePayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class CreateRoomPayload extends BasePayload {

    private String adminId;

    private String adminClientId;

    private String password;

    private List<UserInRoomPayload> userIds;


}
