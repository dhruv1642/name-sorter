package com.example.namesorter.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class NameComparatorTest {

    private final NameParser parser = new NameParser();
    private final NameComparator comparator = new NameComparator();

    private int compare(String a, String b) {
        return comparator.compare(parser.parse(a), parser.parse(b));
    }

    @Test
    void ordersByLastNameFirst() {
        assertTrue(compare("Zed Alvarez", "Adam Archer") < 0);
    }

    @Test
    void ordersByFirstGivenNameWhenLastNamesMatch() {
        assertTrue(compare("Ann Lee", "Bob Lee") < 0);
    }

    @Test
    void ordersBySecondGivenNameWhenFirstMatches() {
        assertTrue(compare("Ann Beth Lee", "Ann Carol Lee") < 0);
    }

    @Test
    void ordersByThirdGivenNameWhenFirstTwoMatch() {
        assertTrue(compare("Ann Beth Carol Lee", "Ann Beth Dana Lee") < 0);
    }

    @Test
    void shorterGivenNameListSortsFirstWhenPrefixMatches() {
        assertTrue(compare("Ann Lee", "Ann Marie Lee") < 0);
    }

    @Test
    void ignoresCase() {
        assertEquals(0, compare("ann lee", "Ann LEE"));
    }

    @Test
    void identicalNamesAreEqual() {
        assertEquals(0, compare("Ann Lee", "Ann Lee"));
    }

    @Test
    void sortsConsistentlyAcrossAList() {
        List<Name> names = List.of(
                parser.parse("Bob Lee"), parser.parse("Ann Lee"), parser.parse("Cat Abbot"));

        List<String> sorted = names.stream().sorted(comparator).map(Name::fullName).toList();

        assertEquals(List.of("Cat Abbot", "Ann Lee", "Bob Lee"), sorted);
    }
}
