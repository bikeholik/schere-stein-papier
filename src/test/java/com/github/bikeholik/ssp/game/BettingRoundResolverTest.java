package com.github.bikeholik.ssp.game;

import com.github.bikeholik.ssp.model.Player;
import com.github.bikeholik.ssp.strategy.BettingStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.github.bikeholik.ssp.model.Shape.PAPER;
import static com.github.bikeholik.ssp.model.Shape.ROCK;
import static com.github.bikeholik.ssp.model.Shape.SCISSORS;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BettingRoundResolverTest {

    @Mock
    private BettingStrategy firstPlayerBettingStrategy;

    @Mock
    private BettingStrategy secondPlayerBettingStrategy;

    private BettingRoundResolver roundResolver;

    private Player firstPlayer;

    private Player secondPlayer;

    @Before
    public void setUp() throws Exception {
        roundResolver = new BettingRoundResolver();
        firstPlayer = new Player("first", firstPlayerBettingStrategy);
        secondPlayer = new Player("second", secondPlayerBettingStrategy);
    }

    @Test
    public void testResolveDraw() throws Exception {
        when(firstPlayerBettingStrategy.getShape()).thenReturn(PAPER);
        when(secondPlayerBettingStrategy.getShape()).thenReturn(PAPER);

        assertThat(roundResolver.resolve(firstPlayer.getBet(), secondPlayer.getBet()), is(Optional.<Player.Bet>empty()));
    }

    @Test
    public void testResolveFirstWins() throws Exception {
        when(firstPlayerBettingStrategy.getShape()).thenReturn(SCISSORS);
        when(secondPlayerBettingStrategy.getShape()).thenReturn(PAPER);

        Player.Bet firstPlayerBet = firstPlayer.getBet();

        assertThat(roundResolver.resolve(firstPlayerBet, secondPlayer.getBet()), is(Optional.of(firstPlayerBet)));
    }

    @Test
    public void testResolveSecondWins() throws Exception {
        when(firstPlayerBettingStrategy.getShape()).thenReturn(SCISSORS);
        when(secondPlayerBettingStrategy.getShape()).thenReturn(ROCK);

        Player.Bet secondPlayerBet = secondPlayer.getBet();

        assertThat(roundResolver.resolve(firstPlayer.getBet(), secondPlayerBet), is(Optional.of(secondPlayerBet)));
    }
}