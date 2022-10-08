package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = read.readLine()) != null) {
                line = line.trim();
                if (line.isBlank() || line.startsWith("#")) {
                    continue;
                }
                int separatorIndex = line.indexOf("=");
                validateLine(line, separatorIndex);
                values.put(line.substring(0, separatorIndex), line.substring(separatorIndex + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    private void validateLine(String line, int index) {
        if (index == -1) {
            throw new IllegalArgumentException(String.format("Pair not found in line %s", line));
        }
        if (index == 0) {
            throw new IllegalArgumentException(String.format("Key not found in line %s", line));
        }
        if (index == line.length() - 1) {
            throw new IllegalArgumentException(String.format("Value not found in line %s", line));
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}