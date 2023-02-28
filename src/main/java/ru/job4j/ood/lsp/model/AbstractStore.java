package ru.job4j.ood.lsp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

abstract class AbstractStore implements Store {
    private final List<Food> content = new ArrayList<>();

    public abstract boolean foodMeetsTheCondition(Food food, Date checkingDate);

    public boolean addFood(Food food, Date checkingDate) {
        if (!foodMeetsTheCondition(food, checkingDate)) {
            return false;
        }
        content.add(food);
        return true;
    }

    public List<Food> getFood() {
        return new ArrayList<>(content);
    }

}
