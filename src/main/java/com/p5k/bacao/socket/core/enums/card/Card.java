package com.p5k.bacao.socket.core.enums.card;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Card {
    // Aces
    ACE_OF_HEARTS(Rank.A, Suit.HEARTS),
    ACE_OF_DIAMONDS(Rank.A, Suit.DIAMONDS),
    ACE_OF_CLUBS(Rank.A, Suit.CLUBS),
    ACE_OF_SPADES(Rank.A, Suit.SPADES),

    // Twos
    TWO_OF_HEARTS(Rank.TWO, Suit.HEARTS),
    TWO_OF_DIAMONDS(Rank.TWO, Suit.DIAMONDS),
    TWO_OF_CLUBS(Rank.TWO, Suit.CLUBS),
    TWO_OF_SPADES(Rank.TWO, Suit.SPADES),

    // Threes
    THREE_OF_HEARTS(Rank.THREE, Suit.HEARTS),
    THREE_OF_DIAMONDS(Rank.THREE, Suit.DIAMONDS),
    THREE_OF_CLUBS(Rank.THREE, Suit.CLUBS),
    THREE_OF_SPADES(Rank.THREE, Suit.SPADES),

    // Fours
    FOUR_OF_HEARTS(Rank.FOUR, Suit.HEARTS),
    FOUR_OF_DIAMONDS(Rank.FOUR, Suit.DIAMONDS),
    FOUR_OF_CLUBS(Rank.FOUR, Suit.CLUBS),
    FOUR_OF_SPADES(Rank.FOUR, Suit.SPADES),

    // Fives
    FIVE_OF_HEARTS(Rank.FIVE, Suit.HEARTS),
    FIVE_OF_DIAMONDS(Rank.FIVE, Suit.DIAMONDS),
    FIVE_OF_CLUBS(Rank.FIVE, Suit.CLUBS),
    FIVE_OF_SPADES(Rank.FIVE, Suit.SPADES),

    // Sixes
    SIX_OF_HEARTS(Rank.SIX, Suit.HEARTS),
    SIX_OF_DIAMONDS(Rank.SIX, Suit.DIAMONDS),
    SIX_OF_CLUBS(Rank.SIX, Suit.CLUBS),
    SIX_OF_SPADES(Rank.SIX, Suit.SPADES),

    // Sevens
    SEVEN_OF_HEARTS(Rank.SEVEN, Suit.HEARTS),
    SEVEN_OF_DIAMONDS(Rank.SEVEN, Suit.DIAMONDS),
    SEVEN_OF_CLUBS(Rank.SEVEN, Suit.CLUBS),
    SEVEN_OF_SPADES(Rank.SEVEN, Suit.SPADES),

    // Eights
    EIGHT_OF_HEARTS(Rank.EIGHT, Suit.HEARTS),
    EIGHT_OF_DIAMONDS(Rank.EIGHT, Suit.DIAMONDS),
    EIGHT_OF_CLUBS(Rank.EIGHT, Suit.CLUBS),
    EIGHT_OF_SPADES(Rank.EIGHT, Suit.SPADES),

    // Nines
    NINE_OF_HEARTS(Rank.NINE, Suit.HEARTS),
    NINE_OF_DIAMONDS(Rank.NINE, Suit.DIAMONDS),
    NINE_OF_CLUBS(Rank.NINE, Suit.CLUBS),
    NINE_OF_SPADES(Rank.NINE, Suit.SPADES),

    // Tens
    TEN_OF_HEARTS(Rank.TEN, Suit.HEARTS),
    TEN_OF_DIAMONDS(Rank.TEN, Suit.DIAMONDS),
    TEN_OF_CLUBS(Rank.TEN, Suit.CLUBS),
    TEN_OF_SPADES(Rank.TEN, Suit.SPADES),

    // Jacks
    JACK_OF_HEARTS(Rank.J, Suit.HEARTS),
    JACK_OF_DIAMONDS(Rank.J, Suit.DIAMONDS),
    JACK_OF_CLUBS(Rank.J, Suit.CLUBS),
    JACK_OF_SPADES(Rank.J, Suit.SPADES),

    // Queens
    QUEEN_OF_HEARTS(Rank.Q, Suit.HEARTS),
    QUEEN_OF_DIAMONDS(Rank.Q, Suit.DIAMONDS),
    QUEEN_OF_CLUBS(Rank.Q, Suit.CLUBS),
    QUEEN_OF_SPADES(Rank.Q, Suit.SPADES),

    // Kings
    KING_OF_HEARTS(Rank.K, Suit.HEARTS),
    KING_OF_DIAMONDS(Rank.K, Suit.DIAMONDS),
    KING_OF_CLUBS(Rank.K, Suit.CLUBS),
    KING_OF_SPADES(Rank.K, Suit.SPADES);

    private final Rank rank;
    private final Suit suit;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getPoints() {
        return rank.getPoints();
    }

    public int getPower() {
        return rank.getPower() * 10 + suit.getStrength();
    }

    @Override
    public String toString() {
        return rank.getDpName() + " " + suit.name();
    }

    @JsonCreator
    public static Card fromValue(String value) {
        for (Card card : values()) {
            if (card.name().equalsIgnoreCase(value)) {
                return card;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

}
