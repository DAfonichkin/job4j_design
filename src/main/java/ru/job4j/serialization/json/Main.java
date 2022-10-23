package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);

        Car car = new Car(100000,
                "Tesla",
                true,
                new String[]{"Made in USA", "Very powerful engine"},
                new Engine(700, 0));
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"price\":100000,"
                        + "\"model\":\"Tesla\","
                        + "\"isElectricCar\":true,"
                        + "\"details\":[\"Made in USA\",\"Very powerful engine\"],"
                        + "\"engine\":{\"power\":700,\"volume\":0} "
                        + "}";
        Car carFromJSON = gson.fromJson(carJson, Car.class);
        System.out.println(carFromJSON);
    }
}