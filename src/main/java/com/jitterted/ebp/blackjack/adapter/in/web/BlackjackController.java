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
        return redirect();
    }

    @GetMapping("/game")
    public String gameView(Model model) {
        populateGameView(model);
        return "blackjack";
    }

    @GetMapping("/done")
    public String doneView(Model model) {
        populateGameView(model);
        model.addAttribute("outcome",
                           game.determineOutcome().displayValue());
        return "done";
    }

    @PostMapping("/hit")
    public String hitCommand() {
        game.playerHits();
        return redirect();
    }

    @PostMapping("/stand")
    public String standCommand() {
        game.playerStands();
        return redirect();
    }

    // MAPPING of Game state to View Page
    private String redirect() {
        if (game.isPlayerDone()) {
            return "redirect:/done";
        }
        return "redirect:/game";
    }

    private void populateGameView(Model model) {
        GameView gameView = GameView.of(game);
        model.addAttribute("gameView", gameView);
    }

}
