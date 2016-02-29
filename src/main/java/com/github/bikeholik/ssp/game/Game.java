package com.github.bikeholik.ssp.game;

import com.github.bikeholik.ssp.model.GameStats;
import com.github.bikeholik.ssp.model.Player;

public class Game {
    private final int roundsCount;
    private final BettingRoundResolver roundResolver;

    public Game(int roundsCount, BettingRoundResolver roundResolver) {
        this.roundsCount = roundsCount;
        this.roundResolver = roundResolver;
    }

    public GameStats play(Player firstPlayer, Player secondPlayer){
        return null;
    }
}
