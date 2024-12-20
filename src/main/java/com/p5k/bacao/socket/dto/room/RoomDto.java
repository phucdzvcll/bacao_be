package com.p5k.bacao.socket.dto.room;

import cn.hutool.core.lang.UUID;
import java.util.List;
import lombok.Data;

@Data
public class RoomDto {

  private String roomId = UUID.randomUUID().toString();

  private String adminId;

  private String adminClientId;

  private String password;

  private String roomName;

  private List<UserInRoomDto> userIds;
}
