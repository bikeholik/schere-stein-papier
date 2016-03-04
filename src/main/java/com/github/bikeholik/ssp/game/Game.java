package com.github.bikeholik.ssp.game;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.IntStream;

import com.github.bikeholik.ssp.model.GameStats;
import com.github.bikeholik.ssp.model.Player;
import com.github.bikeholik.ssp.model.Shape;

public class Game {
    private final int roundsCount;
    private final BettingRoundResolver roundResolver;
    private final Collection<ResultListener> resultListeners;

    public Game(int roundsCount, BettingRoundResolver roundResolver, Collection<ResultListener> resultListeners) {
        this.roundsCount = roundsCount;
        this.roundResolver = roundResolver;
        this.resultListeners = resultListeners;
    }

    public GameStats play(Player firstPlayer, Player secondPlayer){
        return IntStream.range(0, roundsCount)
                .mapToObj(i -> roundResolver.resolve(firstPlayer.getBet(), secondPlayer.getBet()))
                .peek(this::notifyResultListeners)
                .collect(GameStats::new, GameStats::addResult, GameStats::merge);
    }

    private void notifyResultListeners(Optional<Player.Bet> winningBet) {
        resultListeners.forEach(listener -> listener.onResult(winningBet.map(Player.Bet::getShape)));
    }

    public interface ResultListener {
        void onResult(Optional<Shape> winningShape);
    }
}
