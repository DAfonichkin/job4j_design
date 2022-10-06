package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenLineWithoutSeparator() {
        String path = "./data/lineWithoutSeparatorTest.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pair not found");
    }

    @Test
    void whenLineWithoutKey() {
        String path = "./data/lineWithoutKeyTest.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Key not found");
    }

    @Test
    void whenLineWithoutValue() {
        String path = "./data/lineWithoutValueTest.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value not found");
   }

    @Test
    void whenLineWithComment() {
        String path = "./data/commentsTest.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("comment")).isNull();
    }

    @Test
    void whenEmptyLines() {
        String path = "./data/emptyLinesTest.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isNull();
    }

    @Test
    void whenCorrectFile() {
        String path = "./data/correctFileTest.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("url")).isEqualTo("jdbc:postgresql:/127.0.0.1:5432/trackstudio=");
        assertThat(config.value("driver_class")).isEqualTo("org.postgresql.Driver=1");
        assertThat(config.value("username")).isEqualTo("postgres");
    }
}