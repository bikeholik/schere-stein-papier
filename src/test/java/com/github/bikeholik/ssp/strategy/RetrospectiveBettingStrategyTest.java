package com.github.bikeholik.ssp.strategy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;

import com.github.bikeholik.ssp.model.Shape;
import org.junit.Test;


public class RetrospectiveBettingStrategyTest {

    private final RetrospectiveBettingStrategy retrospectiveBettingStrategy = new RetrospectiveBettingStrategy();

    @Test
    public void testGetShape() throws Exception {
        Shape shapeWithDraw = retrospectiveBettingStrategy.getShape();

        assertThat(shapeWithDraw, notNullValue());

        retrospectiveBettingStrategy.onResult(Optional.empty());

        Shape lostBet = retrospectiveBettingStrategy.getShape();

        assertThat(lostBet, not(shapeWithDraw));

        retrospectiveBettingStrategy.onResult(Optional.of(lostBet.getBeatenShape()));

        Shape winningBet = retrospectiveBettingStrategy.getShape();

        assertThat(winningBet, is(lostBet.getBeatenShape()));

        retrospectiveBettingStrategy.onResult(Optional.of(winningBet));

        assertThat(retrospectiveBettingStrategy.getShape(), is(winningBet));

    }
}