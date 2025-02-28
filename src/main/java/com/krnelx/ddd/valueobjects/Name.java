package com.krnelx.ddd.valueobjects;

import com.krnelx.ddd.exceptions.InvalidNameException;
import java.util.Objects;

public final class Name {

    private final String firstName;
    private final String lastName;

    public Name(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidNameException("First name cannot be empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidNameException("Last name cannot be empty");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name name)) return false;
        return firstName.equals(name.firstName) && lastName.equals(name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
