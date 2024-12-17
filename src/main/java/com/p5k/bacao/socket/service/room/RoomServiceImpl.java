package com.p5k.bacao.socket.service.room;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p5k.bacao.http.core.xtools.XChecker;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.payload.room.CreateRoomPayload;
import io.github.dengliming.redismodule.redisearch.RediSearch;
import io.github.dengliming.redismodule.redisearch.client.RediSearchClient;
import io.github.dengliming.redismodule.redisearch.index.Document;
import io.github.dengliming.redismodule.redisearch.search.SearchOptions;
import io.github.dengliming.redismodule.redisearch.search.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl extends RoomService {
    //    private final RedisJSON redisJSON;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RedisTemplate<String, String> redisTemplate;
    private final RediSearchClient rediSearchClient;

    @Override
    public List<RoomDto> getAllRooms() {
//        String queryPath = "$.[*]";
//        return objectMapper.convertValue(
//                redisJSON.get("room", Object.class, new GetArgs().path(queryPath)),
//                new TypeReference<>() {
//                });
        return List.of();

    }

    @Override
    public RoomDto createRoom(CreateRoomPayload createRoomPayload, String userId, String clientId) {


        Boolean roomDto = exitByRoomName(createRoomPayload.getRoomName());

//        SearchResult searchResult = jedisPooled.ftSearch("idx_rooms", "@roomName:" + createRoomPayload.getRoomName());
//
//        if(XChecker.isNotEmpty(searchResult.getDocuments())){
//            RoomDto roomDto1 = new Gson().fromJson(searchResult.getDocuments().get(0).fe, RoomDto.class);
//        }
        return null;


//        String queryPath = "$.[?(@.roomName=='" + createRoomPayload.getRoomName() + "')].roomId";
//        Object roomId = redisJSON.get("room", List.class, new GetArgs().path(queryPath));
//
//        if (XChecker.isEmpty(roomId)) {
//            createRoomPayload.setAdminId(userId);
//            UserInRoomPayload userInRoomPayload = new UserInRoomPayload();
//            userInRoomPayload.setSkSessionId(clientId);
//            userInRoomPayload.setUserId(userId);
//            createRoomPayload.setUserIds(List.of(userInRoomPayload));
//            redisJSON.arrAppend("room", ".", createRoomPayload);
//            return objectMapper.convertValue(createRoomPayload, RoomDto.class);
//        } else {
//            throw new ServiceException(ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_ALREADY_EXITS);
//        }
    }

    @Override
    public RoomDto findRoomById(String roomId) {
//        String queryPath = "$.[?(@.roomId=='" + roomId + "')]";
//        Object room = redisJSON.get("room", Object.class, new GetArgs().path(queryPath));
//        List<RoomDto> rooms = objectMapper.convertValue(room, new TypeReference<>() {
//        });
//        if (rooms.isEmpty()) {
//            throw new ServiceException(ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_NOT_FOUND);
//        }
//        return rooms.get(0);
        return new RoomDto();
    }

    @Override
    public Boolean exitByRoomName(String roomName) {

        RediSearch redisSearch = rediSearchClient.getRediSearch("idx_rooms");
        SearchResult result = redisSearch.search("@roomName:" + roomName, new SearchOptions());
        List<Document> documents = result.getDocuments();
        return XChecker.isNotEmpty(documents);
    }

    @Override
    public List<RoomDto> findRoomByUserId(String userId) {
        return List.of();
    }

    @Override
    public CompletionStage<Long> joinToRoom(String roomId, String userId, String clientId) {
//        UserInRoomPayload userInRoomPayload = new UserInRoomPayload();
//        userInRoomPayload.setSkSessionId(clientId);
//        userInRoomPayload.setUserId(userId);
//        RoomDto roomDto = findRoomById(roomId);
//        if (roomDto.getUserIds().size() > 17) {
//            throw new ServiceException(ServiceCodeEnum.SOCKET_EXCEPTION_ROOM_FULL);
//        }
//
//        return redisJSON.arrAppendAsync("room", "$.[?(@.roomId=='" + roomId + "')].userIds", userInRoomPayload);
        return null;
    }
}
