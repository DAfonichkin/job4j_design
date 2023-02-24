package ru.job4j.ood.lsp.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class Shop extends AbstractStore {

    public Shop(Predicate<Integer> allocateCondition) {
        this.allocateCondition = allocateCondition;
    }
    @Override
    public void addFood(Food food, Date checkingDate) {
        super.addFood(food, checkingDate);
        if (food.getFreshPercent(checkingDate) > 75) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
        }
    }
}
