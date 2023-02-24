package ru.job4j.ood.lsp.model;

import java.util.Date;
import java.util.List;

public interface Store {
    boolean foodMeetsTheCondition(Food food, Date checkingDate);

    void addFood(Food food, Date checkingDate);

    List<Food> getFood();
}
