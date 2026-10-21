package com.example.namesorter.io;

import java.util.List;

/** Where unsorted names come from. */
public interface NameSource {

    /** @return the raw lines of text, one per name. */
    List<String> readLines();
}
