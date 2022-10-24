package ru.job4j.serialization.xml;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "engine")
public class Engine {
    @XmlAttribute
    private int power;
    @XmlAttribute
    private int volume;

    public Engine() {
    }

    public Engine(int power, int volume) {
        this.power = power;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "power=" + power
                + ", volume=" + volume
                + '}';
    }

}
