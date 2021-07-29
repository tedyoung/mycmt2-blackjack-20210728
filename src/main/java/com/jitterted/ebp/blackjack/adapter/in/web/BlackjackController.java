package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlackjackController {

    private final Game game; // don't hold on to references to Entities

    public BlackjackController(Game game) {
        this.game = game;
    }

    @PostMapping("/start-game")
    public String startGame() {
        game.initialDeal();
        return "redirect:/game";
    }

    @GetMapping("/game")
    public String gameView(Model model) {
        GameView gameView = GameView.of(game);
        model.addAttribute("gameView", gameView);
        return "blackjack";
    }

    @GetMapping("/done")
    public String doneView(Model model) {
        GameView gameView = GameView.of(game);
        model.addAttribute("gameView", gameView);
        model.addAttribute("outcome",
                           game.determineOutcome().displayValue());
        return "done";
    }

    @PostMapping("/hit")
    public String hitCommand() {
        game.playerHits();
        if (game.isPlayerDone()) {
            return "redirect:/done";
        }
        return "redirect:/game";
    }

    @PostMapping("/stand")
    public String standCommand() {
        game.playerStands();
        game.dealerTurn();
        return "redirect:/done";
    }

}
