package com.example.namesorter.domain;

import java.util.List;

/**
 * A person's name: one to three given names followed by a last name.
 *
 * <p>Immutable value object. Invalid names cannot be constructed, so the rest of the
 * code never has to re-check these rules.
 */
public record Name(List<String> givenNames, String lastName) {

    public static final int MIN_GIVEN_NAMES = 1;
    public static final int MAX_GIVEN_NAMES = 3;

    public Name {
        if (lastName == null || lastName.isBlank()) {
            throw new InvalidNameException("A name must have a last name");
        }
        if (givenNames == null
                || givenNames.size() < MIN_GIVEN_NAMES
                || givenNames.size() > MAX_GIVEN_NAMES) {
            throw new InvalidNameException(
                    "A name must have between %d and %d given names".formatted(MIN_GIVEN_NAMES, MAX_GIVEN_NAMES));
        }
        givenNames = List.copyOf(givenNames);
    }

    /** The name as it should be displayed, e.g. {@code "Adonis Julius Archer"}. */
    public String fullName() {
        return String.join(" ", givenNames) + " " + lastName;
    }

    @Override
    public String toString() {
        return fullName();
    }
}
