package com.github.bikeholik.ssp.model;

import java.util.function.Supplier;

public enum Shape {
    ROCK("SCISSORS"),
    PAPER("ROCK"),
    SCISSORS("PAPER");

    private final Supplier<Shape> beatenShape;

    Shape(String beatenShape) {
        this.beatenShape = () -> Shape.valueOf(beatenShape);
    }

    public Shape getBeatenShape(){
        return beatenShape.get();
    }
}
