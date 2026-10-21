package com.example.namesorter.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class NameParserTest {

    private final NameParser parser = new NameParser();

    @Test
    void parsesOneGivenName() {
        Name name = parser.parse("Janet Parsons");

        assertEquals(List.of("Janet"), name.givenNames());
        assertEquals("Parsons", name.lastName());
    }

    @Test
    void parsesThreeGivenNames() {
        Name name = parser.parse("Hunter Uriah Mathew Clarke");

        assertEquals(List.of("Hunter", "Uriah", "Mathew"), name.givenNames());
        assertEquals("Clarke", name.lastName());
    }

    @Test
    void toleratesExtraWhitespace() {
        Name name = parser.parse("  Leo   Gardner \t");

        assertEquals("Leo Gardner", name.fullName());
    }

    @Test
    void rejectsSingleWord() {
        assertThrows(InvalidNameException.class, () -> parser.parse("Cher"));
    }

    @Test
    void rejectsTooManyGivenNames() {
        assertThrows(InvalidNameException.class, () -> parser.parse("A B C D Smith"));
    }

    @Test
    void rejectsBlankAndNull() {
        assertThrows(InvalidNameException.class, () -> parser.parse("   "));
        assertThrows(InvalidNameException.class, () -> parser.parse(null));
    }
}
