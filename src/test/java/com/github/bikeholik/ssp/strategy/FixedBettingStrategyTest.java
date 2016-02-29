package com.github.bikeholik.ssp.strategy;

import com.github.bikeholik.ssp.model.Shape;
import org.junit.Test;

import static java.util.stream.IntStream.range;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FixedBettingStrategyTest {

    private final BettingStrategy strategy = new FixedBettingStrategy(Shape.PAPER);

    @Test
    public void testGetShape() throws Exception {
        Shape firstBet = strategy.getShape();
        range(0, 10).forEach(i -> assertThat(strategy.getShape(), is(firstBet)));
    }
}