package com.github.bikeholik.ssp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameStats {
    private final Map<Player, Integer> winnings = new HashMap<>();
    private int draws = 0;

    public Map<Player, Integer> getWinnings() {
        return winnings;
    }

    public int getDraws() {
        return draws;
    }

    public void addResult(Optional<Player.Bet> winner) {

    }
}
