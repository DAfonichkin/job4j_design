package ru.job4j.serialization.json;

public class Engine {
    private int power;
    private int volume;

    public Engine(int power, int volume) {
        this.power = power;
        this.volume = volume;
    }

    public int getPower() {
        return power;
    }

    public int getVolume() {
        return volume;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setVolume(int volume) {
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
