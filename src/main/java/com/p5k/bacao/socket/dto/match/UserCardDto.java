package com.p5k.bacao.socket.dto.match;

import com.p5k.bacao.socket.core.enums.card.Card;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UserCardDto {

    private String userId;

    private int score;

    private Card strongestCard;

    private ArrayList<Card> cardList = new ArrayList<>();
}
