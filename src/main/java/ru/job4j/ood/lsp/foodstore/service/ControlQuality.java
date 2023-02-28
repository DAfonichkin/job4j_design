package ru.job4j.ood.lsp.foodstore.service;

import ru.job4j.ood.lsp.foodstore.model.Food;
import ru.job4j.ood.lsp.foodstore.model.Store;

import java.util.Date;
import java.util.List;

public class ControlQuality {
    private List<Food> foodList;
    private List<Store> storeList;
    private Date checkingDate;

    public ControlQuality(List<Food> foodList, List<Store> storeList, Date checkingDate) {
        this.foodList = foodList;
        this.storeList = storeList;
        this.checkingDate = checkingDate;
    }

    public void allocateFood() {
        for (Food food : foodList) {
            for (Store store : storeList) {
                if (store.foodMeetsTheCondition(food, checkingDate)) {
                    store.addFood(food, checkingDate);
                }
            }
        }
    }
}
