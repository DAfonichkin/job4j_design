package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getElement(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getElement(value, comparator.reversed());
    }

    private <T> T getElement(List<T> value, Comparator<T> comparator) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        value.sort(comparator.reversed());
        return value.get(0);
    }
}