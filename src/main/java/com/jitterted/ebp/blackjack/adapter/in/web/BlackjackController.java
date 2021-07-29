package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.springframework.stereotype.Controller;
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

}
