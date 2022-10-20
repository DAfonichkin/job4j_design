package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        validate(argsName);
        List<Integer> filteredColumnsIndexes = new ArrayList<>();
        List<String> filterList = List.of(argsName.get("filter").split(","));
        try (Scanner lineScanner = new Scanner(new File(argsName.get("path")));
             PrintWriter out = new PrintWriter(new FileOutputStream((argsName.get("out"))))) {
            String del = argsName.get("delimiter");
            boolean itsFirstLine = true;
            while (lineScanner.hasNextLine()) {
                String line = lineScanner.nextLine();
                StringJoiner resultLine = new StringJoiner(del);
                List<String> values = List.of(line.split(del));
                if (itsFirstLine) {
                    for (String filter : filterList) {
                        for (String value : values) {
                            if (Objects.equals(filter, value)) {
                                filteredColumnsIndexes.add(values.indexOf(value));
                            }
                        }
                    }
                }
                for (int index : filteredColumnsIndexes) {
                    resultLine.add(values.get(index));
                }
                itsFirstLine = false;
                if ("stdout".equals(argsName.get("out"))) {
                    System.out.println(resultLine);
                } else {
                    out.println(resultLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        Path dir = Paths.get(argsName.get("path"));
        if (!Files.exists(dir)) {
            throw new IllegalArgumentException(String.format("%s is not exist", argsName.get("path")));
        }
        if (!dir.toString().endsWith(".csv") && !dir.toString().endsWith(".CSV")) {
            throw new IllegalArgumentException(String.format("%s is not .csv file", argsName.get("path")));
        }
        String delimiter = argsName.get("delimiter");
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException("Delimiter is empty");
        }
        String filter = argsName.get("filter");
        if (filter.isEmpty()) {
            throw new IllegalArgumentException("Filter is empty");
        }
        if (!"stdout".equals(argsName.get("out"))) {
            Path out = Paths.get(argsName.get("out"));
            if (!Files.isDirectory(out.getParent())) {
                throw new IllegalArgumentException(String.format("Directory %s is not exist", out.getParent()));
            }
        }
    }

    public static void main(String[] args) {
        ArgsName params = ArgsName.of(args);
        try {
            handle(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
