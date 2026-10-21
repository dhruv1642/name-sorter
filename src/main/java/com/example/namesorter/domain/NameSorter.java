package com.example.namesorter.domain;

import java.util.Comparator;
import java.util.List;

/** Sorts names using a supplied {@link Comparator}, without modifying the input list. */
public class NameSorter {

    private final Comparator<Name> comparator;

    public NameSorter(Comparator<Name> comparator) {
        this.comparator = comparator;
    }

    public List<Name> sort(List<Name> names) {
        return names.stream().sorted(comparator).toList();
    }
}
