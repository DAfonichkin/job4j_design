package ru.job4j.ood.icp;

public class SportCar implements Car {
    @Override
    public void go() {

    }

    @Override
    public void getPassengers() {
        throw new IllegalStateException("Sport car have only one place");
    }
}
