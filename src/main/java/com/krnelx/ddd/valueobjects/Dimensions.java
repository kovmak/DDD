package com.krnelx.ddd.valueobjects;

import com.krnelx.ddd.exceptions.DimensionExceededException;
import com.krnelx.ddd.exceptions.InvalidDimensionException;
import java.util.Objects;

public final class Dimensions {

    private final double length;
    private final double width;
    private final double height;

    public static final double MAX_LENGTH = 100.0;
    public static final double MAX_WIDTH = 100.0;
    public static final double MAX_HEIGHT = 100.0;

    public Dimensions(double length, double width, double height) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new InvalidDimensionException("Dimensions must be greater than zero.");
        }
        this.length = length;
        this.width = width;
        this.height = height;
        validateMaxDimensions();
    }

    private void validateMaxDimensions() {
        if (length > MAX_LENGTH || width > MAX_WIDTH || height > MAX_HEIGHT) {
            throw new DimensionExceededException("Dimensions exceed the maximum allowed size.");
        }
    }

    public double calculateVolume() {
        return length * width * height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dimensions that)) return false;
        return Double.compare(that.length, length) == 0 &&
            Double.compare(that.width, width) == 0 &&
            Double.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }

    @Override
    public String toString() {
        return "Dimensions{" +
            "length=" + length +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
