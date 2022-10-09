package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

class AnalizyTest {

    @Test
    void unavailableTest(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analizy analyzer = new Analizy();
        analyzer.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        assertThat(rsl.toString()).isEqualTo("10:57:01;10:59:01"
                + System.lineSeparator() + "11:01:02;11:02:02");
    }

}