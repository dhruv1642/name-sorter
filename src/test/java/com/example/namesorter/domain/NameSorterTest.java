package com.example.namesorter.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class NameSorterTest {

    private final NameParser parser = new NameParser();
    private final NameSorter sorter = new NameSorter(new NameComparator());

    private List<Name> parseAll(String... lines) {
        return java.util.Arrays.stream(lines).map(parser::parse).toList();
    }

    private List<String> sortedFullNames(String... lines) {
        return sorter.sort(parseAll(lines)).stream().map(Name::fullName).toList();
    }

    @Test
    void sortsTheAssessmentExample() {
        List<String> sorted = sortedFullNames(
                "Janet Parsons",
                "Vaughn Lewis",
                "Adonis Julius Archer",
                "Shelby Nathan Yoder",
                "Marin Alvarez",
                "London Lindsey",
                "Beau Tristan Bentley",
                "Leo Gardner",
                "Hunter Uriah Mathew Clarke",
                "Mikayla Lopez",
                "Frankie Conner Ritter");

        assertEquals(
                List.of(
                        "Marin Alvarez",
                        "Adonis Julius Archer",
                        "Beau Tristan Bentley",
                        "Hunter Uriah Mathew Clarke",
                        "Leo Gardner",
                        "Vaughn Lewis",
                        "London Lindsey",
                        "Mikayla Lopez",
                        "Janet Parsons",
                        "Frankie Conner Ritter",
                        "Shelby Nathan Yoder"),
                sorted);
    }

    @Test
    void emptyListSortsToEmptyList() {
        assertEquals(List.of(), sorter.sort(List.of()));
    }

    @Test
    void doesNotModifyTheInputList() {
        List<Name> input = new ArrayList<>(parseAll("Bob Lee", "Ann Abbot"));

        sorter.sort(input);

        assertEquals("Bob Lee", input.get(0).fullName());
    }
}
