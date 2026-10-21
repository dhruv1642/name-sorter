package com.example.namesorter.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Reads names from a UTF-8 text file, one name per line. Blank lines are skipped. */
public class FileNameSource implements NameSource {

    private final Path path;

    public FileNameSource(Path path) {
        this.path = path;
    }

    @Override
    public List<String> readLines() {
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                    .filter(line -> !line.isBlank())
                    .toList();
        } catch (IOException e) {
            throw new UncheckedIOException("Could not read names from " + path, e);
        }
    }
}
