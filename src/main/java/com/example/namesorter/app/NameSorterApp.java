package com.example.namesorter.app;

import com.example.namesorter.domain.Name;
import com.example.namesorter.domain.NameParser;
import com.example.namesorter.domain.NameSorter;
import com.example.namesorter.io.NameSink;
import com.example.namesorter.io.NameSource;
import java.util.List;

/**
 * The use case: read names, sort them, write them out.
 *
 * <p>Knows nothing about files or the console; those are supplied through
 * {@link NameSource} and {@link NameSink}.
 */
public class NameSorterApp {

    private final NameSource source;
    private final NameSink sink;
    private final NameParser parser;
    private final NameSorter sorter;

    public NameSorterApp(NameSource source, NameSink sink, NameParser parser, NameSorter sorter) {
        this.source = source;
        this.sink = sink;
        this.parser = parser;
        this.sorter = sorter;
    }

    public void run() {
        List<Name> names = source.readLines().stream().map(parser::parse).toList();
        List<String> sortedLines = sorter.sort(names).stream().map(Name::fullName).toList();
        sink.write(sortedLines);
    }
}
