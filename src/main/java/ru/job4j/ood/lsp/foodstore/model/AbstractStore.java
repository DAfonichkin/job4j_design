package ru.job4j.ood.lsp.foodstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
