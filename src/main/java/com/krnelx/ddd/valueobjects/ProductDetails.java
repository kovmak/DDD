package com.krnelx.ddd.valueobjects;

import com.krnelx.ddd.exceptions.InvalidProductDetailsException;
import java.util.Objects;

public final class ProductDetails {

    private final String name;
    private final String description;
    private final Dimensions dimensions;

    public ProductDetails(String name, String description, Dimensions dimensions) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductDetailsException("Product name cannot be empty.");
        }
        this.name = name;
        this.description = description;
        this.dimensions = dimensions;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public String getShortDescription() {
        if (description.length() > 50) {
            return description.substring(0, 50) + "...";
        }
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetails that)) return false;
        return name.equals(that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(dimensions, that.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, dimensions);
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", dimensions=" + dimensions +
            '}';
    }
}
