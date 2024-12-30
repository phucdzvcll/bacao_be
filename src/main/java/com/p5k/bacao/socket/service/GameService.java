package com.p5k.bacao.socket.service;

import com.p5k.bacao.socket.core.enums.card.Card;
import com.p5k.bacao.socket.core.enums.card.CombinationType;
import com.p5k.bacao.socket.core.enums.card.Rank;
import com.p5k.bacao.socket.dto.match.UserCardDto;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  // calculate point
  private int calculateScore(List<Card> cards) {
    int totalPoints = cards.stream().mapToInt(Card::getPoints).sum();
    return totalPoints % 10;
  }

  // find the strongest card
  private Card findStrongestCard(List<Card> cards) {
    return cards.stream().max(Comparator.comparingInt(Card::getPower)).orElse(null);
  }

  // Three of a kind
  private boolean isThreeOfAKind(List<Card> cards) {
    return cards.get(0).getRank() == cards.get(1).getRank() &&
        cards.get(1).getRank() == cards.get(2).getRank();
  }

  // Straight
  private boolean isStraight(List<Card> cards) {

    cards.sort(Comparator.comparingInt(card -> card.getRank().getPower()));

    int powerCard1 = cards.get(0).getRank().getPower();
    int powerCard2 = cards.get(1).getRank().getPower();
    int powerCard3 = cards.get(2).getRank().getPower();

    return (powerCard2 == powerCard1 + 1 && powerCard3 == powerCard2 + 1);   // Q-K-A
  }

  // (J, Q, K)
  private boolean isThreeFaceCards(List<Card> cards) {
    return cards.stream().allMatch(card -> card.getRank() == Rank.J ||
        card.getRank() == Rank.Q ||
        card.getRank() == Rank.K);
  }

  //Set combination type
  //find the winner
  public UserCardDto findWinnerWithRules(List<UserCardDto> users) {
    UserCardDto winner = null;
    CombinationType highestRank = CombinationType.NORMAL; // Quy tắc thắng cao nhất
    Card strongestCardOfWinner = null;

    for (UserCardDto user : users) {
      List<Card> cards = user.getCardList();
      CombinationType rank;

      if (isThreeOfAKind(cards)) {
        rank = CombinationType.THREE_OF_KIND;
      } else if (isStraight(cards)) {
        rank = CombinationType.STRAIGHT;
      } else if (isThreeFaceCards(cards)) {
        rank = CombinationType.THREE_FACE;
      } else {
        rank = CombinationType.NORMAL;
      }

      int score = calculateScore(cards);
      Card strongestCard = findStrongestCard(cards);

      user.setScore(score);
      user.setStrongestCard(strongestCard);
      user.setCombinationType(rank);

      if (winner == null ||
          rank.getPower() > highestRank.getPower() ||
          (rank == highestRank && score > calculateScore(winner.getCardList())) ||
          (rank == highestRank && score == calculateScore(winner.getCardList())
              && strongestCard.getPower() > strongestCardOfWinner.getPower())) {
        winner = user;
        highestRank = rank;
        strongestCardOfWinner = strongestCard;
      }
    }

    return winner;
  }
}
