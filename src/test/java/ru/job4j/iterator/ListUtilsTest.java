package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterLastEl() {
        ListUtils.addAfter(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 3, 2);
    }

    @Test
    void whenRemoveIfNegative() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 0, -5);
        ListUtils.addAfter(input, 0, -10);
        ListUtils.removeIf(input, (el) -> el < 0);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfNoElementsToRemove() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.removeIf(input, (el) -> el < 0);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenReplaceIfNegative() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 0, -5);
        ListUtils.addAfter(input, 0, -10);
        ListUtils.replaceIf(input, (el) -> el < 0, 0);
        assertThat(input).hasSize(5).containsSequence(1, 0, 0, 2, 3);
    }

    @Test
    void whenReplaceIfNoElementsToReplace() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.replaceIf(input, (el) -> el < 0, 0);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(1).containsSequence(2);
    }

    @Test
    void whenRemoveAllWithEmptyElements() {
        List<Integer> elements = new ArrayList<>();
        ListUtils.addAfter(input, 0, 2);
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }
}