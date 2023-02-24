package ru.job4j.ood.lsp.model;

import java.util.List;
import java.util.function.Predicate;

public class Warehouse extends AbstractStore {
    public Warehouse(Predicate<Integer> allocateCondition) {
        this.allocateCondition = allocateCondition;
    }
}
