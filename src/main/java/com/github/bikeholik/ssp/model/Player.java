package com.github.bikeholik.ssp.model;

import com.github.bikeholik.ssp.strategy.BettingStrategy;

public class Player {
    private final String name;
    private final BettingStrategy bettingStrategy;

    public Player(String name, BettingStrategy bettingStrategy) {
        this.name = name;
        this.bettingStrategy = bettingStrategy;
    }

    public Bet getBet(){
        return new Bet(this, bettingStrategy.getShape());
    }

    public static class Bet {
        private final Player player;
        private final Shape shape;

        Bet(Player player, Shape shape) {
            this.player = player;
            this.shape = shape;
        }

        public Player getPlayer() {
            return player;
        }

        public Shape getShape() {
            return shape;
        }
    }
}
