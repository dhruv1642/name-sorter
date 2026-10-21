package com.example.namesorter.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.namesorter.domain.InvalidNameException;
import com.example.namesorter.domain.NameComparator;
import com.example.namesorter.domain.NameParser;
import com.example.namesorter.domain.NameSorter;
import com.example.namesorter.io.NameSink;
import com.example.namesorter.io.NameSource;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class NameSorterAppTest {

    /** In-memory sink that remembers what it was given. */
    private static class RecordingSink implements NameSink {
        final List<String> written = new ArrayList<>();

        @Override
        public void write(List<String> lines) {
            written.addAll(lines);
        }
    }

    private NameSorterApp appReading(List<String> lines, NameSink sink) {
        NameSource source = () -> lines;
        return new NameSorterApp(source, sink, new NameParser(), new NameSorter(new NameComparator()));
    }

    @Test
    void readsSortsAndWritesNames() {
        RecordingSink sink = new RecordingSink();

        appReading(List.of("Bob Lee", "Ann Abbot", "Ann Lee"), sink).run();

        assertEquals(List.of("Ann Abbot", "Ann Lee", "Bob Lee"), sink.written);
    }

    @Test
    void invalidNameStopsTheRunBeforeAnythingIsWritten() {
        RecordingSink sink = new RecordingSink();

        assertThrows(InvalidNameException.class, () -> appReading(List.of("Ann Lee", "Cher"), sink).run());

        assertEquals(List.of(), sink.written);
    }
}
