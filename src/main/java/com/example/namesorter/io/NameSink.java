package com.example.namesorter.io;

import java.util.List;

/** Where sorted names go. */
public interface NameSink {

    void write(List<String> lines);
}
