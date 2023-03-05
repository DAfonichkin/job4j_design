package ru.job4j.ood.lsp.parking.model;

import java.util.ArrayList;
import java.util.List;

public class StandardParking implements Parking {

    private List<Car> cars = new ArrayList<>();
    private int truckPlacesCount;
    private int carPlacesCount;

    public StandardParking(int carPlacesCount, int truckPlacesCount) {
        if (carPlacesCount < 0 || truckPlacesCount < 0) {
            throw new IllegalArgumentException("Count of places must be more than 1");
        }
        this.truckPlacesCount = truckPlacesCount;
        this.carPlacesCount = carPlacesCount;
    }

    @Override
    public boolean park(Car car) {
        if (truckPlacesCount >= 1 && car.getSize() > PassengerCar.SIZE) {
            cars.add(car);
            truckPlacesCount--;
            return true;
        }
        if (carPlacesCount >= car.getSize()) {
            cars.add(car);
            carPlacesCount -= car.getSize();
            return true;
        }
        return false;
    }

    @Override
    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }
}
