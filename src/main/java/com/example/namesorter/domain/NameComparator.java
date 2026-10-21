package com.example.namesorter.domain;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Orders names by last name, then by given names (first given name, then second, then third).
 *
 * <p>Comparison is case-insensitive and accent-aware. A name with fewer given names sorts
 * before a longer name that starts with the same given names
 * (e.g. "Ann Lee" before "Ann Marie Lee").
 */
public class NameComparator implements Comparator<Name> {

    private final Collator collator;

    public NameComparator() {
        this(Locale.ENGLISH);
    }

    public NameComparator(Locale locale) {
        this.collator = Collator.getInstance(locale);
        this.collator.setStrength(Collator.SECONDARY); // ignore case, respect accents
    }

    @Override
    public int compare(Name a, Name b) {
        int byLastName = collator.compare(a.lastName(), b.lastName());
        if (byLastName != 0) {
            return byLastName;
        }
        return compareGivenNames(a.givenNames(), b.givenNames());
    }

    private int compareGivenNames(List<String> a, List<String> b) {
        int shared = Math.min(a.size(), b.size());
        for (int i = 0; i < shared; i++) {
            int result = collator.compare(a.get(i), b.get(i));
            if (result != 0) {
                return result;
            }
        }
        return Integer.compare(a.size(), b.size());
    }
}
