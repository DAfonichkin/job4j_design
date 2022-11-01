package ru.job4j.io.filefinder;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class FileFinder {
    public static void main(String[] args) {
        ArgsName params = ArgsName.of(args);
        validate(params);
        Path start = Paths.get(params.get("d"));
        String typeOfSearch = params.get("t");
        String name = params.get("n");
        Predicate<Path> condition = p -> p.toFile().getName().equals(name);
        if ("regex".equals(typeOfSearch)) {
            condition = p -> p.toFile().getName().matches(name);
        }
        if ("mask".equals(typeOfSearch)) {
            String regExp = convertMaskToRegexp(name);
            condition = p -> p.toFile().getName().matches(regExp);
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(params.get("o"))
                ))) {
            for (Path el : Search.search(start, condition)) {
                out.println(el.toAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        Path dir = Paths.get(argsName.get("d"));
        if (!Files.exists(dir)) {
            throw new IllegalArgumentException(String.format("Directory %s is not exist", dir));
        }
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException(String.format("Directory %s is not exist", dir));
        }
        String typeOfSearch = argsName.get("t");
        if ((!"mask".equals(typeOfSearch)) && (!"regex".equals(typeOfSearch)) && (!"name".equals(typeOfSearch))) {
            throw new IllegalArgumentException(String.format("Type of search %s is not supported. "
                    + "Please, choose one of this types: \"name\", \"mask\" or \"regex\"", typeOfSearch));
        }
        String expression = argsName.get("n");
        if (expression.isEmpty()) {
            throw new IllegalArgumentException("Expression is empty. Please, use argument \"n\"");
        }
        String outputFile = argsName.get("o");
        if (outputFile.isEmpty()) {
            throw new IllegalArgumentException("Output file name is empty. Please, use argument \"o\"");
        }
        if (!outputFile.contains(".")
                || outputFile.indexOf(".") == 0
                || outputFile.indexOf(".") == outputFile.length() - 1) {
            throw new IllegalArgumentException("Output file name is not valid.");
        }
    }

    public static String convertMaskToRegexp(String mask) {
        return mask.replace(".", "\\.")
                .replace("*", ".*")
                .replace("?", ".");
    }
}
