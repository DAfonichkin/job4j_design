package ru.job4j.ood.lsp.model;

import java.util.List;
import java.util.function.Predicate;

public class Trash extends AbstractStore {

    public Trash(Predicate<Integer> allocateCondition) {
        this.allocateCondition = allocateCondition;
    }
}
