package com.github.bikeholik.ssp.strategy;

import com.github.bikeholik.ssp.model.Shape;

import java.util.Random;

public class RandomBettingStrategy implements BettingStrategy {
    private final Random random = new Random(System.currentTimeMillis());
    private final Shape[] allShapes = Shape.values();

    @Override
    public Shape getShape() {
        return allShapes[random.nextInt(allShapes.length)];
    }
}
