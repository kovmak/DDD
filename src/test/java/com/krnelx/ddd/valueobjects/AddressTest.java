package com.krnelx.ddd.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import com.krnelx.ddd.exceptions.InvalidAddressException;
import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void shouldCreateValidAddress() {
        Address address = new Address("Ukraine", "Kyiv", "Street 1", "12345");
        assertEquals("Ukraine", address.getCountry());
        assertEquals("Kyiv", address.getCity());
        assertEquals("Street 1", address.getStreet());
        assertEquals("12345", address.getPostalCode());
    }

    @Test
    void shouldThrowExceptionForInvalidPostalCode() {
        assertThrows(InvalidAddressException.class, () -> new Address("Ukraine", "Kyiv", "Street 1", "invalid"));
    }
}
