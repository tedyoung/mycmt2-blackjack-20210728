package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Hand;
import com.jitterted.ebp.blackjack.domain.Rank;
import com.jitterted.ebp.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandDisplayTest {

    @Test
    public void displayFirstCard() throws Exception {
        Hand hand = new Hand(
                List.of(
                        new Card(Suit.HEARTS, Rank.ACE)));

        assertThat(ConsoleHand.displayFirstCard(hand))
                .isEqualTo("[31m┌─────────┐[1B[11D│A        │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│        A│[1B[11D└─────────┘");
    }

    @Test
    public void cardsAsStringTranslatesHandToDisplayableString() throws Exception {
        Hand hand = new Hand(List.of(new Card(Suit.CLUBS, Rank.TEN),
                                     new Card(Suit.SPADES, Rank.FIVE)));

        assertThat(ConsoleHand.cardsAsString(hand))
                .isEqualTo("[30m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘[6A[1C[30m┌─────────┐[1B[11D│5        │[1B[11D│         │[1B[11D│    ♠    │[1B[11D│         │[1B[11D│        5│[1B[11D└─────────┘");
    }
}
