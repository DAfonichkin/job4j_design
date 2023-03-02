package ru.job4j.ood.lsp.parking.model;

import java.util.List;

public interface Parking {
    boolean park(Car car);
    List<Car> getCars();
}
