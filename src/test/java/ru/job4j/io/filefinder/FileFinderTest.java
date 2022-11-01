package ru.job4j.io.filefinder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FileFinderTest {

    @Test
    void convertMaskToRegexp() {
        String mask = "*.?xt";
        String result = FileFinder.convertMaskToRegexp(mask);
        assertThat(result).isEqualTo(".*\\..xt");
    }
}