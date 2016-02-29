package com.github.bikeholik.ssp.model;

import org.junit.Test;

import static com.github.bikeholik.ssp.model.Shape.PAPER;
import static com.github.bikeholik.ssp.model.Shape.ROCK;
import static com.github.bikeholik.ssp.model.Shape.SCISSORS;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShapeTest {

    @Test
    public void testGetBeatenShape() throws Exception {
        assertThat(PAPER.getBeatenShape(), is(ROCK));
        assertThat(ROCK.getBeatenShape(), is(SCISSORS));
        assertThat(SCISSORS.getBeatenShape(), is(PAPER));
    }
}