package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});

        final Gson gson = new GsonBuilder().create();

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

        Car car = new Car(100000,
                "Tesla",
                true,
                new String[]{"Made in USA", "Very powerful engine"},
                new Engine(700, 0));

        final String carJson =
                "{"
                        + "\"price\":100000,"
                        + "\"model\":\"Tesla\","
                        + "\"isElectricCar\":true,"
                        + "\"details\":[\"Made in USA\",\"Very powerful engine\"],"
                        + "\"engine\":{\"power\":700,\"volume\":0} "
                        + "}";
        Car carFromJSON = gson.fromJson(carJson, Car.class);

        JSONObject jsonEngine = new JSONObject("{\"engine\":{\"power\":700,\"volume\":0}}");

        List<String> list = new ArrayList<>();
        list.add("Made in USA");
        list.add("Very powerful engine");
        JSONArray jsonDetails = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("price", car.getPrice());
        jsonObject.put("model", car.getModel());
        jsonObject.put("isElectricCar", car.isElectricCar());
        jsonObject.put("details", jsonDetails);
        jsonObject.put("engine", jsonEngine);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(car));

    }
}