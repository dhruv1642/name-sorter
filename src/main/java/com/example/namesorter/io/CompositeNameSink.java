package com.example.namesorter.io;

import java.util.List;

/** Sends the same lines to several sinks, e.g. the screen and a file. */
public class CompositeNameSink implements NameSink {

    private final List<NameSink> sinks;

    public CompositeNameSink(List<NameSink> sinks) {
        this.sinks = List.copyOf(sinks);
    }

    @Override
    public void write(List<String> lines) {
        sinks.forEach(sink -> sink.write(lines));
    }
}
