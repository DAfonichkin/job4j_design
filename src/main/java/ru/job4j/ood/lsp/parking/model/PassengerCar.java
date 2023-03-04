package ru.job4j.ood.lsp.parking.model;

import ru.job4j.ood.lsp.parking.model.Car;

public class PassengerCar implements Car {
    public static final int SIZE = 1;
    private final String name;

    public PassengerCar(String name) {
        this.name = name;
    }

    @Override
    public int getSize() {
        return SIZE;
    }
}
