package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getElement(value, (t1, t2) -> comparator.compare(t1, t2) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getElement(value, (t1, t2) -> comparator.compare(t1, t2) < 0);
    }

    private <T> T getElement(List<T> value, BiPredicate<T, T> predicate) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        T result = value.get(0);
        for (T element : value) {
            if (predicate.test(element, result)) {
                result = element;
            }
        }
        return result;
    }
}