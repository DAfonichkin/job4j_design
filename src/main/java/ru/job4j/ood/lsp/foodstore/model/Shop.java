package ru.job4j.ood.lsp.foodstore.model;

import java.util.Date;

public class Shop extends AbstractStore {

    @Override
    public boolean foodMeetsTheCondition(Food food, Date checkingDate) {
        int freshPercent = food.getFreshPercent(checkingDate);
        return freshPercent < 100 && freshPercent >= 25;
    }

    @Override
    public boolean addFood(Food food, Date checkingDate) {
        if (!super.addFood(food, checkingDate)) {
            return false;
        }
        if (food.getFreshPercent(checkingDate) > 75) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
        }
        return true;
    }
}
