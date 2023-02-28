package ru.job4j.ood.lsp.foodstore.model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class FoodTest {

    @Test
    void getFreshPercent() {
        Food food = new Food("Egg",
                new GregorianCalendar(2023, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2023, Calendar.FEBRUARY, 28).getTime(),
                100,
                10);
        Date checkingDate = new GregorianCalendar(2023, Calendar.FEBRUARY, 14).getTime();
        assertThat(food.getFreshPercent(checkingDate)).isEqualTo(48);
    }
}