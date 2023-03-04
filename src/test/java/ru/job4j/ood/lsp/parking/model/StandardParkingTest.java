package ru.job4j.ood.lsp.parking.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.PassengerCar;
import ru.job4j.ood.lsp.parking.model.Truck;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
class StandardParkingTest {
    
    @Test
    void whenHasNotPlacesForParkingOnCarPlaces() {
        Car truck = new Truck("Truck", 2);
        Car car1 = new PassengerCar("Car 1");
        Car car2 = new PassengerCar("Car 2");
        Parking parking = new StandardParking(2, 0);
        parking.park(car1);
        parking.park(car2);
        assertThat(parking.park(truck)).isFalse();
    }

    @Test
    void whenHasPlacesForParkingOnCarPlaces() {
        Car truck = new Truck("Truck", 2);
        Car car1 = new PassengerCar("Car 1");
        Parking parking = new StandardParking(3, 0);
        parking.park(truck);
        parking.park(car1);
        assertThat(parking.getCars()).isEqualTo(List.of(truck, car1));
    }

    @Test
    void whenHasNotPlacesForParkingOnAllPlaces() {
        Car truck = new Truck("Truck", 2);
        Car car1 = new PassengerCar("Car 1");
        Car car2 = new PassengerCar("Car 2");
        Parking parking = new StandardParking(1, 1);
        parking.park(truck);
        parking.park(car1);
        assertThat(parking.park(car2)).isFalse();
    }

    @Test
    void whenHasPlacesForParkingOnAllPlaces() {
        Car truck = new Truck("Truck", 2);
        Car car1 = new PassengerCar("Car 1");
        Car car2 = new PassengerCar("Car 2");
        Parking parking = new StandardParking(2, 1);
        parking.park(truck);
        parking.park(car1);
        parking.park(car2);
        assertThat(parking.getCars()).containsAll(List.of(car1, car2, truck));
    }
}