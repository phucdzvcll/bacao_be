package com.p5k.bacao.socket.service.match;

import com.p5k.bacao.socket.dto.match.MatchDto;
import com.p5k.bacao.socket.dto.room.RoomDto;

public interface MatchService {

    MatchDto createMatch(RoomDto roomDto);

}
