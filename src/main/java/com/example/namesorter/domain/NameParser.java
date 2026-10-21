package com.example.namesorter.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Turns a line of text such as {@code "Hunter Uriah Mathew Clarke"} into a {@link Name}.
 *
 * <p>Words are separated by any whitespace. The final word is the last name; every word
 * before it is a given name.
 */
public class NameParser {

    public Name parse(String line) {
        if (line == null || line.isBlank()) {
            throw new InvalidNameException("A name cannot be blank");
        }

        String[] words = line.trim().split("\\s+");
        List<String> givenNames = Arrays.asList(words).subList(0, words.length - 1);
        String lastName = words[words.length - 1];

        return new Name(givenNames, lastName);
    }
}
