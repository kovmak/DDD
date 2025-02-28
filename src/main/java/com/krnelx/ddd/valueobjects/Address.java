package com.krnelx.ddd.valueobjects;

import com.krnelx.ddd.exceptions.InvalidAddressException;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Address {

    private final String country;
    private final String city;
    private final String street;
    private final String postalCode;

    private static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("\\d{5}");

    public Address(String country, String city, String street, String postalCode) {
        if (country == null || country.trim().isEmpty()) {
            throw new InvalidAddressException("Country cannot be empty.");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new InvalidAddressException("City cannot be empty.");
        }
        if (street == null || street.trim().isEmpty()) {
            throw new InvalidAddressException("Street cannot be empty.");
        }
        if (postalCode == null || !POSTAL_CODE_PATTERN.matcher(postalCode).matches()) {
            throw new InvalidAddressException("Postal code is invalid.");
        }
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + country + " - " + postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return country.equals(address.country) &&
            city.equals(address.city) &&
            street.equals(address.street) &&
            postalCode.equals(address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, postalCode);
    }
}
