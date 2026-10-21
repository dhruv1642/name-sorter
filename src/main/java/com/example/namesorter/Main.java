package com.example.namesorter;

import com.example.namesorter.app.NameSorterApp;
import com.example.namesorter.domain.InvalidNameException;
import com.example.namesorter.domain.NameComparator;
import com.example.namesorter.domain.NameParser;
import com.example.namesorter.domain.NameSorter;
import com.example.namesorter.io.CompositeNameSink;
import com.example.namesorter.io.ConsoleNameSink;
import com.example.namesorter.io.FileNameSink;
import com.example.namesorter.io.FileNameSource;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;

/** Command-line entry point. Wires the pieces together and reports errors. */
public final class Main {

    static final String OUTPUT_FILE_NAME = "sorted-names-list.txt";

    private Main() {}

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: name-sorter <path-to-unsorted-names-file>");
            System.exit(2);
        }

        try {
            new NameSorterApp(
                            new FileNameSource(Path.of(args[0])),
                            new CompositeNameSink(List.of(
                                    new ConsoleNameSink(), new FileNameSink(Path.of(OUTPUT_FILE_NAME)))),
                            new NameParser(),
                            new NameSorter(new NameComparator()))
                    .run();
        } catch (InvalidNameException | UncheckedIOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
