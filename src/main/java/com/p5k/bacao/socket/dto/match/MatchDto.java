package com.p5k.bacao.socket.dto.match;

import com.p5k.bacao.socket.core.enums.card.Card;
import lombok.Data;

import java.util.List;

@Data
public class MatchDto {

    private String roomId;

    private String matchId;

    private UserCardDto winner;

    private List<UserCardDto> userCardDtoList;

    private List<Card> initCard;

}
