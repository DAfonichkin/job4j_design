package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNameIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkNameDoesNotContainEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("first"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: first does not contain the symbol \"=\"");
    }

    @Test
    void checkNameDoesNotContainAKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=first"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: =first does not contain a key");
    }

    @Test
    void checkNameDoesNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("first="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: first= does not contain a value");
    }
}