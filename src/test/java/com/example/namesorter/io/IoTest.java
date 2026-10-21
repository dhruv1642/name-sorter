package com.example.namesorter.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class IoTest {

    @TempDir
    Path tempDir;

    @Test
    void fileSourceReadsLinesAndSkipsBlankOnes() throws Exception {
        Path file = tempDir.resolve("names.txt");
        Files.writeString(file, "Ann Lee\n\nBob Lee\n   \n");

        assertEquals(List.of("Ann Lee", "Bob Lee"), new FileNameSource(file).readLines());
    }

    @Test
    void fileSourceReportsMissingFile() {
        FileNameSource source = new FileNameSource(tempDir.resolve("missing.txt"));

        assertThrows(UncheckedIOException.class, source::readLines);
    }

    @Test
    void fileSinkWritesAndOverwrites() throws Exception {
        Path file = tempDir.resolve("out.txt");
        FileNameSink sink = new FileNameSink(file);

        sink.write(List.of("old one", "old two", "old three"));
        sink.write(List.of("Ann Lee"));

        assertEquals(List.of("Ann Lee"), Files.readAllLines(file));
    }

    @Test
    void consoleSinkPrintsOneLinePerName() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bytes, true, StandardCharsets.UTF_8);

        new ConsoleNameSink(out).write(List.of("Ann Lee", "Bob Lee"));

        assertEquals(
                List.of("Ann Lee", "Bob Lee"),
                bytes.toString(StandardCharsets.UTF_8).lines().toList());
    }

    @Test
    void compositeSinkForwardsToEverySink() {
        List<String> first = new ArrayList<>();
        List<String> second = new ArrayList<>();

        new CompositeNameSink(List.of(first::addAll, second::addAll)).write(List.of("Ann Lee"));

        assertEquals(List.of("Ann Lee"), first);
        assertEquals(List.of("Ann Lee"), second);
    }
}
