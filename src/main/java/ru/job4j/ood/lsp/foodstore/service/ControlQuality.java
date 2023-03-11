package ru.job4j.ood.lsp.foodstore.service;

import ru.job4j.ood.lsp.foodstore.model.Food;
import ru.job4j.ood.lsp.foodstore.model.Store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControlQuality {
    private List<Food> foodList = new ArrayList<>();
    private List<Store> storeList = new ArrayList<>();
    private Date checkingDate;

    public ControlQuality(List<Food> foodList, List<Store> storeList, Date checkingDate) {
        this.foodList.addAll(foodList);
        this.storeList.addAll(storeList);
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

    public void resort() {
        foodList.clear();
        for (Store store : storeList) {
            foodList.addAll(store.getFood());
            store.clearFood();
        }
        allocateFood();
    }
}
