package com.github.bikeholik.ssp.game;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.summingInt;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.github.bikeholik.ssp.model.GameStats;
import com.github.bikeholik.ssp.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    public static final int ROUNDS_COUNT = 10;

    private Game game;

    @Mock
    private Player player;
    @Mock
    private BettingRoundResolver roundResolver;

    @Before
    public void setUp() throws Exception {
        game = new Game(ROUNDS_COUNT, roundResolver, emptyList());
        when(player.getBet()).then(invocationOnMock -> mock(Player.Bet.class));
    }

    @Test
    public void testPlay() throws Exception {
        when(roundResolver.resolve(any(Player.Bet.class), any(Player.Bet.class)))
                .thenAnswer(invocationOnMock -> Optional.of(invocationOnMock.getArguments()[0]))
                .thenReturn(Optional.<Player.Bet>empty());

        GameStats gameStats = game.play(player, player);

        assertThat(gameStats, notNullValue());
        assertThat(computeRoundsCount(gameStats), is(ROUNDS_COUNT));
    }

    private int computeRoundsCount(GameStats gameStats) {
        return gameStats.getDraws() + gameStats.getWinnings().values().stream().collect(summingInt(value -> value));
    }
}