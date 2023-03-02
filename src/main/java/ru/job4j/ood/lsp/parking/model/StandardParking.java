package ru.job4j.ood.lsp.parking.model;

import java.util.ArrayList;
import java.util.List;

public class StandardParking implements Parking {

    private List<Car> passCars = new ArrayList<>();
    private List<Car> trucks = new ArrayList<>();
    private final int truckPlacesCount;
    private final int carPlacesCount;

    public StandardParking(int carPlacesCount, int truckPlacesCount) {
        this.truckPlacesCount = truckPlacesCount;
        this.carPlacesCount = carPlacesCount;
    }

    @Override
    public boolean park(Car car) {
        return false;
    }

    @Override
    public List<Car> getCars() {
        List<Car> result = new ArrayList<>(passCars);
        result.addAll(trucks);
        return result;
    }
}
