package com.example.namesorter.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    void fullNameJoinsGivenNamesThenLastName() {
        Name name = new Name(List.of("Hunter", "Uriah", "Mathew"), "Clarke");

        assertEquals("Hunter Uriah Mathew Clarke", name.fullName());
    }

    @Test
    void acceptsOneToThreeGivenNames() {
        new Name(List.of("A"), "Z");
        new Name(List.of("A", "B"), "Z");
        new Name(List.of("A", "B", "C"), "Z");
    }

    @Test
    void rejectsNoGivenNames() {
        assertThrows(InvalidNameException.class, () -> new Name(List.of(), "Smith"));
    }

    @Test
    void rejectsMoreThanThreeGivenNames() {
        assertThrows(InvalidNameException.class, () -> new Name(List.of("A", "B", "C", "D"), "Smith"));
    }

    @Test
    void rejectsBlankLastName() {
        assertThrows(InvalidNameException.class, () -> new Name(List.of("Ann"), " "));
    }

    @Test
    void givenNamesAreDefensivelyCopied() {
        List<String> given = new java.util.ArrayList<>(List.of("Ann"));
        Name name = new Name(given, "Lee");

        given.add("Marie");

        assertEquals(List.of("Ann"), name.givenNames());
    }
}
