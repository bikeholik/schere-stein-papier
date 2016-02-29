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
        if (winner.isPresent()) {
            Integer currentWinnings = winnings.computeIfAbsent(winner.get().getPlayer(), player -> 0);
            winnings.put(winner.get().getPlayer(), currentWinnings + 1);
        } else {
            draws++;
        }
    }

    public void merge(GameStats gameStats) {
        throw new UnsupportedOperationException();
    }
}
