package ru.job4j.ood.lsp.parking.model;

public class Truck implements Car {
    private final int size;
    private final String name;

    public Truck(String name, int size) {
        if (size <= PassengerCar.SIZE) {
            throw new IllegalArgumentException("Size must be more than 1");
        }
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
