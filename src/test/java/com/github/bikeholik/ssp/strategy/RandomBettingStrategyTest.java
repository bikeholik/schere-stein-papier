package com.github.bikeholik.ssp.strategy;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class RandomBettingStrategyTest {

    private BettingStrategy strategy = new RandomBettingStrategy();

    @Test
    public void testGetShape() throws Exception {
        range(0, 10).forEach(i -> assertThat(strategy.getShape(), notNullValue()));
    }
}