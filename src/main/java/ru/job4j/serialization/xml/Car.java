package ru.job4j.serialization.xml;

import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private int price;
    @XmlAttribute
    private String model;
    @XmlAttribute
    private boolean isElectricCar;
    @XmlElementWrapper(name = "details")
    @XmlElement(name = "detail")
    private String[] details;
    private Engine engine;

    public Car() {
    }
    public Car(int price, String model, boolean isElectricCar, String[] details, Engine engine) {
        this.price = price;
        this.model = model;
        this.isElectricCar = isElectricCar;
        this.details = details;
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
