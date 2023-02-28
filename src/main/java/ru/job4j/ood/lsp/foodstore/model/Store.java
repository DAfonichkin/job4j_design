package ru.job4j.ood.lsp.foodstore.model;

import java.util.Date;
import java.util.List;

public interface Store {
    boolean foodMeetsTheCondition(Food food, Date checkingDate);

    boolean addFood(Food food, Date checkingDate);

    List<Food> getFood();
}
