package com.jitterted.ebp.blackjack.domain.port;

import com.jitterted.ebp.blackjack.domain.Deck;
import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.StubDeck;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class GameMonitorTest {

    @Test
    public void playerStandsCompletesGameSendsToMonitor() throws Exception {
        // creates the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Deck playerNotDealtBlackjackDeck = StubDeck.playerIsNotDealtBlackjack();
        Game game = new Game(playerNotDealtBlackjackDeck, gameMonitorSpy);
        game.initialDeal();

        game.playerStands();

        // verify that the roundCompleted method was called with any instance of a Game class
        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerHitsAndGoesBustResultInGameSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Deck playerHitsAndGoesBustDeck = StubDeck.playerHitsAndGoesBust();
        Game game = new Game(playerHitsAndGoesBustDeck, gameMonitorSpy);
        game.initialDeal();

        game.playerHits();

        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerHitsAndDoesNotGoBustResultsInNothingSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Deck playerHistAndDoesNotGoBust = StubDeck.playerHitsAndDoesNotGoesBust();
        Game game = new Game(playerHistAndDoesNotGoBust, gameMonitorSpy);
        game.initialDeal();

        game.playerHits();

        verify(gameMonitorSpy, never()).roundCompleted(any(Game.class));
    }

    @Test
    public void playerDealtBlackjackResultsInGameSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Deck playerDealtBlackjackDeck = StubDeck.playerDealtBlackjack();
        Game game = new Game(playerDealtBlackjackDeck, gameMonitorSpy);

        game.initialDeal();

        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }


}