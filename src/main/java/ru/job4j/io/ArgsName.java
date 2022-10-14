package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if ((value) == null) {
            throw new IllegalArgumentException("Parameter not found");
        }
        return value;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No one parameters not found");
        }
        for (String parameter : args) {
            int separatorIndex = parameter.indexOf("=");
            validateParameter(parameter, separatorIndex);
            values.put(parameter.substring(1, separatorIndex), parameter.substring(separatorIndex + 1));
        }
    }

    private void validateParameter(String line, int index) {
        if (line.indexOf("-") != 0) {
            throw new IllegalArgumentException(String.format("Symbol '-' not found in line %s", line));
        }
        if (index == -1) {
            throw new IllegalArgumentException(String.format("Pair not found in line %s", line));
        }
        if (index == 1) {
            throw new IllegalArgumentException(String.format("Key not found in line %s", line));
        }
        if (index == line.length() - 1) {
            throw new IllegalArgumentException(String.format("Value not found in line %s", line));
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}