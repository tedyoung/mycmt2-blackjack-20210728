package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    public void whenPlayerHasHigherValueHandThanDealerOutcomeIsPlayerBeatsDealer() throws Exception {
        Deck stubDeck = new StubDeck(Rank.TEN, Rank.EIGHT,
                                     Rank.QUEEN, Rank.JACK);
        Game game = new Game(stubDeck);
        game.initialDeal();

        game.playerStands();

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

    @Test
    public void whenPlayerHitsAndGoesBustOutcomeIsPlayerBusted() throws Exception {
        Deck stubDeck = StubDeck.playerHitsAndGoesBust();
        Game game = new Game(stubDeck);
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_BUSTED);
    }

    @Test
    public void newGameThenIsPlayerDoneIsFalse() throws Exception {
        Game game = new Game();

        assertThat(game.isPlayerDone())
                .isFalse();
    }

    @Test
    public void whenInitialDealNotGameOverThenIsPlayerDoneIsFalse() throws Exception {
        Deck stubDeck = new StubDeck(Rank.NINE, Rank.SEVEN,
                                     Rank.JACK, Rank.EIGHT);
        Game game = new Game(stubDeck);

        game.initialDeal();

        assertThat(game.isPlayerDone())
                .isFalse();
    }

    @Test
    public void playerDealtBlackjackImmediatelyWinsBlackjack() throws Exception {
        Deck stubDeck = StubDeck.playerDealtBlackjack();
        Game game = new Game(stubDeck);

        game.initialDeal();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_WINS_BLACKJACK);

        assertThat(game.isPlayerDone())
                .isTrue();
    }

    @Test
    public void standResultsInDealerDrawingCardOnTheirTurn() throws Exception {
        Deck dealerBeatsPlayerAfterDrawingAdditionalCardDeck =
                new StubDeck(Rank.TEN, Rank.QUEEN,
                             Rank.NINE, Rank.FIVE,
                             Rank.SIX);
        Game game = new Game(dealerBeatsPlayerAfterDrawingAdditionalCardDeck);
        game.initialDeal();

        game.playerStands();

        assertThat(game.dealerHand().cards())
                .hasSize(3);
    }


}