package com.p5k.bacao.socket.service.match;

import com.p5k.bacao.socket.core.enums.RoomStatus;
import com.p5k.bacao.socket.core.enums.UserStateEnum;
import com.p5k.bacao.socket.core.enums.card.Card;
import com.p5k.bacao.socket.dto.match.MatchDto;
import com.p5k.bacao.socket.dto.match.UserCardDto;
import com.p5k.bacao.socket.dto.room.RoomDto;
import com.p5k.bacao.socket.dto.room.UserInRoomDto;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JacksonCodec;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchServiceImpl implements MatchService {

    final static String maxPrefix = "match:";

    final RedissonClient redissonClient;

    public MatchServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public MatchDto createMatch(RoomDto roomDto) {
        MatchDto matchDto = new MatchDto();
        matchDto.setMatchId(UUID.randomUUID().toString().replaceAll("-", ""));
        List<Card> deck = initDeck();
        Collections.shuffle(deck);

        matchDto.setInitCard(List.copyOf(deck));

        ArrayList<UserCardDto> userCardDtos = new ArrayList<>();
        for (UserInRoomDto user : roomDto.getUserIds()) {
            UserCardDto userCardDto = new UserCardDto();
            userCardDto.setUserId(user.getUserId());
            userCardDtos.add(userCardDto);
        }

        int turn = 0;
        while (turn < 3) {
            for (UserCardDto userCardDto : userCardDtos) {
                userCardDto.getCardList().add(deck.remove(0));
            }
            turn++;
        }

        matchDto.setUserCardDtoList(userCardDtos);

        String winner = null;
        int highestScore = -1;
        Card strongestCardOfWinner = null;
        for (UserCardDto userCardDto : userCardDtos) {
            int score = calculateScore(userCardDto.getCardList());
            userCardDto.setScore(score);
            Card strongestCard = findStrongestCard(userCardDto.getCardList());
            userCardDto.setStrongestCard(strongestCard);

            if (winner == null || score > highestScore ||
                    (score == highestScore && strongestCard.getPower() > strongestCardOfWinner.getPower())) {
                winner = userCardDto.getUserId();
                highestScore = score;
                strongestCardOfWinner = strongestCard;
            }
        }
        matchDto.setWinner(winner);
        JacksonCodec<MatchDto> matchDtoCodec = new JacksonCodec<>(MatchDto.class);
        JacksonCodec<RoomDto> roomDtoCodec = new JacksonCodec<>(RoomDto.class);
        RBucket<MatchDto> matchBucket = redissonClient.getJsonBucket(maxPrefix + matchDto.getMatchId(),
                matchDtoCodec);

        RBucket<RoomDto> roomBucket = redissonClient.getJsonBucket("room:" + roomDto.getRoomId(),
                roomDtoCodec);
        matchBucket.set(matchDto);
        roomDto.setRoomStatus(RoomStatus.RUNNING);
        roomDto.getUserIds().forEach(userInRoomDto -> userInRoomDto.setUserStateEnum(UserStateEnum.PLAYING));
        roomBucket.set(roomDto);
        return matchBucket.get();
    }

    public List<Card> initDeck() {
        return new ArrayList<>(EnumSet.allOf(Card.class));
    }

    private int calculateScore(List<Card> cards) {
        int totalPoints = cards.stream().mapToInt(Card::getPoints).sum();
        return totalPoints % 10;
    }

    private Card findStrongestCard(List<Card> cards) {
        return cards.stream().max(Comparator.comparingInt(Card::getPower)).orElse(null);
    }
}
