package ru.job4j.ood.lsp.parking.model;

public class Truck implements Car {
    private final int size;
    private final String name;

    public Truck(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
