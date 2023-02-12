package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class StringGeneratorTest {

    @Test
    void whenHaveNotKeysAndHaveNotArgsThenException() {
        Generator stringGenerator = new StringGenerator();
        Map<String, String> keys = new HashMap<>();
        String template = "";
        assertThatThrownBy(() -> stringGenerator.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenHaveKeysAndHaveNotArgsThenException() {
        Generator stringGenerator = new StringGenerator();
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        String template = "";
        assertThatThrownBy(() -> stringGenerator.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenHaveNotKeysAndHaveArgsThenException() {
        Generator stringGenerator = new StringGenerator();
        Map<String, String> keys = new HashMap<>();
        String template = "I am a ${name}, Who are ${subject}?";
        assertThatThrownBy(() -> stringGenerator.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeysAndArgsAreNotMatchThenException() {
        Generator stringGenerator = new StringGenerator();
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        String template = "Who are ${subject}?";
        assertThatThrownBy(() -> stringGenerator.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeysMoreThanArgsThenException() {
        Generator stringGenerator = new StringGenerator();
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        keys.put("subject", "Ivan");
        String template = "Who are ${subject}?";
        assertThatThrownBy(() -> stringGenerator.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeysLessThanArgsThenException() {
        Generator stringGenerator = new StringGenerator();
        Map<String, String> keys = new HashMap<>();
        keys.put("subject", "Ivan");
        String template = "I am a ${name}, Who are ${subject}?";
        assertThatThrownBy(() -> stringGenerator.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenOneArgThenString() {
        Generator stringGenerator = new StringGenerator();
        Map<String, String> keys = new HashMap<>();
        keys.put("subject", "Ivan");
        String template = "Who are ${subject}?";
        assertThat(stringGenerator.produce(template, keys)).isEqualTo("Who are Ivan?");
    }

    @Test
    void whenManyArgsThenString() {
        Generator stringGenerator = new StringGenerator();
        Map<String, String> keys = new HashMap<>();
        keys.put("subject", "Ivan");
        keys.put("name", "Petr");
        String template = "I am a ${name}, Who are ${subject}?";
        assertThat(stringGenerator.produce(template, keys)).isEqualTo("I am a Petr, Who are Ivan?");
    }
}