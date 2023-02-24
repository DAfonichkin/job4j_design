package ru.job4j.ood.lsp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

abstract class AbstractStore implements Store {
    List<Food> content = new ArrayList<>();
    Predicate<Integer> allocateCondition;

    public boolean foodMeetsTheCondition(Food food, Date checkingDate) {
        return allocateCondition.test(food.getFreshPercent(checkingDate));
    }

    public void addFood(Food food, Date checkingDate) {
        content.add(food);
    }

    public List<Food> getFood() {
        return content;
    }

}
