package com.github.bikeholik.ssp.strategy;

import com.github.bikeholik.ssp.model.Shape;

public class FixedBettingStrategy implements BettingStrategy {
    private final Shape shape;

    public FixedBettingStrategy(Shape shape) {
        this.shape = shape;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
