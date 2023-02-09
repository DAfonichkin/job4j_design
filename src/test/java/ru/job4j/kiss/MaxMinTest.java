package ru.job4j.kiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    private List<Integer> testList = new ArrayList<>();
    private Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);

    @BeforeEach
    void init() {
        testList.add(1);
        testList.add(2);
        testList.add(3);
    }

    @Test
    void whenFindMax() {
        assertThat(new MaxMin().max(testList, comparator)).isEqualTo(3);
    }

    @Test
    void whenFindMin() {
        assertThat(new MaxMin().min(testList, comparator)).isEqualTo(1);
    }

    @Test
    void whenEmptyListMax() {
        MaxMin maxMin = new MaxMin();
        testList.clear();
        assertThatThrownBy(() -> maxMin.max(testList, comparator))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEmptyListMin() {
        MaxMin maxMin = new MaxMin();
        testList.clear();
        assertThatThrownBy(() -> maxMin.min(testList, comparator))
                .isInstanceOf(IllegalArgumentException.class);
    }
}