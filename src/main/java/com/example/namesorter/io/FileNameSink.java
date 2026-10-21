package com.example.namesorter.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Writes names to a UTF-8 text file, creating it or overwriting it if it already exists. */
public class FileNameSink implements NameSink {

    private final Path path;

    public FileNameSink(Path path) {
        this.path = path;
    }

    @Override
    public void write(List<String> lines) {
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not write names to " + path, e);
        }
    }
}
