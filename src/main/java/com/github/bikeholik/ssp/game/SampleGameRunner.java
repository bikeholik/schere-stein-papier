package com.github.bikeholik.ssp.game;

import static java.util.Collections.singletonList;

import com.github.bikeholik.ssp.model.GameStats;
import com.github.bikeholik.ssp.model.Player;
import com.github.bikeholik.ssp.strategy.RandomBettingStrategy;
import com.github.bikeholik.ssp.strategy.RetrospectiveBettingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleGameRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        Player playerPlayingWithRockAlways = new Player("rock-playing", new FixedBettingStrategy(ROCK));
        Player playerPlayingRandomly = new Player("randomly-playing", new RandomBettingStrategy());

        RetrospectiveBettingStrategy retrospectiveBettingStrategy = new RetrospectiveBettingStrategy();
        Player playerWithRetrospectiveStrategy = new Player("retro-playing", retrospectiveBettingStrategy);

        Game game = new Game(100, new BettingRoundResolver(), singletonList(retrospectiveBettingStrategy));

        GameStats gameStats = game.play(playerWithRetrospectiveStrategy, playerPlayingRandomly);

        gameStats.getWinnings().entrySet()
                .forEach(entry -> logger.info("player={} winningsCount={}", entry.getKey(), entry.getValue()));
        logger.info("drawsCount={}", gameStats.getDraws());
    }
}
