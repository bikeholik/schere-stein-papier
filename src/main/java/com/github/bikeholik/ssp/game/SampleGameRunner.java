package com.github.bikeholik.ssp.game;

import com.github.bikeholik.ssp.model.GameStats;
import com.github.bikeholik.ssp.model.Player;
import com.github.bikeholik.ssp.model.Shape;
import com.github.bikeholik.ssp.strategy.FixedBettingStrategy;
import com.github.bikeholik.ssp.strategy.RandomBettingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static com.github.bikeholik.ssp.model.Shape.ROCK;

@Component
public class SampleGameRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Player playerPlayingWithRockAlways = new Player("rock-playing", new FixedBettingStrategy(ROCK));
        Player playerPlayingRandomly = new Player("randomly-playing", new RandomBettingStrategy());

        Game game = new Game(100, new BettingRoundResolver());

        GameStats gameStats = game.play(playerPlayingWithRockAlways, playerPlayingRandomly);

        gameStats.getWinnings().entrySet()
                .forEach(entry -> logger.info("player={} winnginsCount={}", entry.getKey(), entry.getValue()));
        logger.info("drawsCount={}", gameStats.getDraws());
    }
}
