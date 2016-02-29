package com.github.bikeholik.ssp.game;

import com.github.bikeholik.ssp.model.Player;

import java.util.Optional;

public class BettingRoundResolver {
    Optional<Player.Bet> resolve(Player.Bet firstPlayerBet, Player.Bet secondPlayerBet) {
        if (isDraw(firstPlayerBet, secondPlayerBet)) {
            return Optional.empty();
        }
        if (doesFirstBeatSecond(firstPlayerBet, secondPlayerBet)) {
            return Optional.of(firstPlayerBet);
        } else {
            assertSecondBeatsFirst(firstPlayerBet, secondPlayerBet);
            return Optional.of(secondPlayerBet);
        }
    }

    private boolean doesFirstBeatSecond(Player.Bet firstPlayerBet, Player.Bet secondPlayerBet) {
        return firstPlayerBet.getShape().getBeatenShape() == secondPlayerBet.getShape();
    }

    private boolean isDraw(Player.Bet firstPlayerBet, Player.Bet secondPlayerBet) {
        return firstPlayerBet.getShape() == secondPlayerBet.getShape();
    }

    private void assertSecondBeatsFirst(Player.Bet firstPlayerBet, Player.Bet secondPlayerBet) {
        if (secondPlayerBet.getShape().getBeatenShape() != firstPlayerBet.getShape()) {
            throw new UnsupportedOperationException("Playing against the rules?");
        }
    }
}
