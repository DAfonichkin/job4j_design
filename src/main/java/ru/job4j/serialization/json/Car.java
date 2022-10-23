package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private int price;
    private String model;
    private boolean isElectricCar;
    private String[] details;
    private Engine engine;

    public Car(int price, String model, boolean isElectricCar, String[] details, Engine engine) {
        this.price = price;
        this.model = model;
        this.isElectricCar = isElectricCar;
        this.details = details;
        this.engine = engine;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isElectricCar() {
        return isElectricCar;
    }

    public void setElectricCar(boolean electricCar) {
        isElectricCar = electricCar;
    }

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{"
                + "price=" + price
                + ", model='" + model + '\''
                + ", isElectricCar=" + isElectricCar
                + ", details=" + Arrays.toString(details)
                + ", engine=" + engine
                + '}';
    }
}
