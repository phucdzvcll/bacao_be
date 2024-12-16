package com.p5k.bacao.socket.dto.room;

import com.redis.om.spring.annotations.Indexed;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@RedisHash("room")
public class RoomDto {
    @Id
    private String roomId;

    private String adminId;

    private String adminClientId;

    private String password;

    @Indexed
    private String roomName;

    private List<UserInRoomDto> userIds;
}
