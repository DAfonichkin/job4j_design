package ru.job4j.ood.lsp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    private Food food;
    private Date checkingDate;
    private Store store;

    @BeforeEach
    void initiate() {
        food = new Food("Egg",
                new GregorianCalendar(2023, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2023, Calendar.FEBRUARY, 28).getTime(),
                100,
                10);
        Predicate<Integer> condition = p -> p < 25 && p >= 0;
        store = new Warehouse(condition);
    }

    @Test
    void whenFoodMeetsConditionThanTrue() {
        checkingDate = new GregorianCalendar(2023, Calendar.FEBRUARY, 1).getTime();
        assertThat(store.foodMeetsTheCondition(food, checkingDate)).isEqualTo(true);
    }

    @Test
    void whenFoodNotMeetsConditionThanFalse() {
        checkingDate = new GregorianCalendar(2023, Calendar.FEBRUARY, 28).getTime();
        assertThat(store.foodMeetsTheCondition(food, checkingDate)).isEqualTo(false);
    }

    @Test
    void whenAddFood() {
        store.addFood(food, checkingDate);
        assertThat(store.getFood()).isEqualTo(List.of(food));
    }

}