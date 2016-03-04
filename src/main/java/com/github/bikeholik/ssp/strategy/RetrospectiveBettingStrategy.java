package com.github.bikeholik.ssp.strategy;

import static java.util.Comparator.comparingInt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.github.bikeholik.ssp.game.Game;
import com.github.bikeholik.ssp.model.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetrospectiveBettingStrategy implements BettingStrategy, Game.ResultListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Map<Shape, Integer> stats = new HashMap<>();
    private Shape lastShape;

    public RetrospectiveBettingStrategy() {
        Stream.of(Shape.values()).forEach(shape -> stats.put(shape, 0));
    }

    @Override
    public Shape getShape() {
        logger.debug("stats={}", stats);
        lastShape = stats.entrySet().stream().max(comparingInt(Map.Entry::getValue)).get().getKey();
        return lastShape;
    }

    @Override
    public void onResult(Optional<Shape> winningShape) {
        if (winningShape.isPresent()) {
            stats.put(lastShape, stats.getOrDefault(lastShape, 0) + getBoost(lastShape, winningShape.get()));
        } else {
            stats.put(lastShape, stats.getOrDefault(lastShape, 0) - 1);
        }
    }

    private static int getBoost(Shape betShape, Shape winningShape) {
        return betShape == winningShape ? 1 : -2;
    }
}
