package com.p5k.bacao.socket.service.room;

import static com.p5k.bacao.socket.core.enums.RedisIndex.ROOM_BY_NAME;
import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.dto.room.UserInRoomDto;
import com.p5k.bacao.socket.payload.room.CreateRoomPayload;
import com.p5k.bacao.socket.payload.room.UserInRoomPayload;
import com.p5k.bacao.socket.service.RoomDtoCodec;
import io.github.dengliming.redismodule.redisjson.RedisJSON;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RSearch;
import org.redisson.api.RedissonClient;
import org.redisson.api.search.query.QueryOptions;
import org.redisson.api.search.query.SearchResult;
import org.redisson.codec.JacksonCodec;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl extends RoomService {

  static final String roomPrefix = "room:";

  private final RedisJSON redisJSON;
  private final RedissonClient redisson;

  JacksonCodec<RoomDto> jsonCodec = new JacksonCodec<>(RoomDto.class);

  @Override
  public List<Object> getAllRooms() {
    RSearch rSearch = redisson.getSearch(new RoomDtoCodec());
    SearchResult r = rSearch.search(ROOM_BY_NAME.getIndex(), "*", QueryOptions.defaults()
        .returnAttributes());

    Stream<Object> stream = r.getDocuments().stream().map(
        document -> document.getAttributes().get("$"));
    return stream.toList();


  }

  @Override
  public RoomDto createRoom(CreateRoomPayload createRoomPayload, String userId, String clientId) {

    XChecker.isTrueThruMsg(XChecker.isNotNull(findRoomName(createRoomPayload.getRoomName())),
        ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_ALREADY_EXITS);

    RoomDto roomDto = new RoomDto();
    roomDto.setRoomName(createRoomPayload.getRoomName());
    roomDto.setPassword(createRoomPayload.getPassword());
    roomDto.setAdminId(userId);
    roomDto.setAdminClientId(clientId);
    UserInRoomDto e1 = new UserInRoomDto();
    e1.setUserId(userId);
    e1.setSkSessionId(clientId);
    roomDto.setUserIds(List.of(e1));
    RBucket<RoomDto> buget = redisson.getJsonBucket(roomPrefix + roomDto.getRoomId(),
        jsonCodec);

    buget.set(roomDto);

    return buget.get();
  }

  @Override
  public RoomDto findRoomById(String roomId) {
    RBucket<RoomDto> buget = redisson.getJsonBucket(roomPrefix + roomId,
        jsonCodec);
    return buget.get();
  }


  @Override
  public RoomDto findRoomName(String roomName) {
    RSearch rSearch = redisson.getSearch(new RoomDtoCodec());
    SearchResult r = rSearch.search(ROOM_BY_NAME.getIndex(), "@roomName:" + roomName,
        QueryOptions.defaults()
            .returnAttributes());
    if (r.getDocuments().isEmpty()) {
      return null;
    }
    return (RoomDto) r.getDocuments().get(0).getAttributes().get("$");
  }

  @Override
  public CompletionStage<Long> joinToRoom(String roomId, String userId, String clientId) {
    UserInRoomPayload userInRoomPayload = new UserInRoomPayload();
    userInRoomPayload.setSkSessionId(clientId);
    userInRoomPayload.setUserId(userId);
    return redisJSON.arrAppendAsync(roomPrefix + roomId, "$.userIds", userInRoomPayload);
  }
}
