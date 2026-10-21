package com.example.namesorter.io;

import java.io.PrintStream;
import java.util.List;

/** Prints names to a {@link PrintStream} (the screen, by default), one per line. */
public class ConsoleNameSink implements NameSink {

    private final PrintStream out;

    public ConsoleNameSink() {
        this(System.out);
    }

    public ConsoleNameSink(PrintStream out) {
        this.out = out;
    }

    @Override
    public void write(List<String> lines) {
        lines.forEach(out::println);
    }
}
