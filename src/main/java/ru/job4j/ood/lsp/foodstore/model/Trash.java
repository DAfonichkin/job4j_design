package ru.job4j.ood.lsp.foodstore.model;

import java.util.Date;

public class Trash extends AbstractStore {

    @Override
    public boolean foodMeetsTheCondition(Food food, Date checkingDate) {
        int freshPercent = food.getFreshPercent(checkingDate);
        return freshPercent >= 100;
    }
}
