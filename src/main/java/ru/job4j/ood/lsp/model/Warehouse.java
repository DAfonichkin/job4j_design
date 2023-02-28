package ru.job4j.ood.lsp.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse extends AbstractStore {

    @Override
    public boolean foodMeetsTheCondition(Food food, Date checkingDate) {
        int freshPercent = food.getFreshPercent(checkingDate);
        return freshPercent < 25;
    }
}
