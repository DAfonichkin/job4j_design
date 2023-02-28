package ru.job4j.ood.lsp.foodstore.service;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void allocateFood() {
        Date checkingDate = new GregorianCalendar(2023, Calendar.FEBRUARY, 21).getTime();
        Food food1 = new Food("Egg",
                new GregorianCalendar(2023, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2023, Calendar.FEBRUARY, 27).getTime(),
                100,
                10);
        Food food2 = new Food("Milk",
                new GregorianCalendar(2023, Calendar.FEBRUARY, 20).getTime(),
                new GregorianCalendar(2023, Calendar.FEBRUARY, 28).getTime(),
                100,
                10);
        Food food3 = new Food("Meat",
                new GregorianCalendar(2023, Calendar.FEBRUARY, 20).getTime(),
                new GregorianCalendar(2023, Calendar.FEBRUARY, 23).getTime(),
                100,
                10);
        Food food4 = new Food("Fruit",
                new GregorianCalendar(2023, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2023, Calendar.FEBRUARY, 20).getTime(),
                100,
                10);
        List<Food> foodList = List.of(food1, food2, food3, food4);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> storeList = List.of(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality(foodList, storeList, checkingDate);
        controlQuality.allocateFood();
        assertThat(warehouse.getFood()).isEqualTo(List.of(food2));
        assertThat(shop.getFood()).isEqualTo(List.of(food1, food3));
        assertThat(trash.getFood()).isEqualTo(List.of(food4));
        assertThat(food1.getPrice()).isEqualTo(90);
        assertThat(food3.getPrice()).isEqualTo(100);
    }
}