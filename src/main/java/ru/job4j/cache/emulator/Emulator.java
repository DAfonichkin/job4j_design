package ru.job4j.cache.emulator;

import ru.job4j.cache.DirFileCache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {

    public static final int LOAD = 1;
    public static final int GET = 2;
    public static final String MENU = """
                Введите 1 для загрузки файла в кэш.
                Введите 2 для получения файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static final String PATH = "Укажите путь.";
    public static final String FILENAME_LOAD = "Введите имя файла для загрузки в кэш.";
    public static final String FILENAME_GET = "Введите имя файла для выгрузки из кэша.";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(PATH);
        String dir = sc.nextLine();
        Path pathDir = Paths.get(dir);
        if (!Files.exists(pathDir) || !Files.isDirectory(pathDir)) {
            throw new IllegalArgumentException();
        }
        DirFileCache dirFileCache = new DirFileCache(dir);
        start(sc, dirFileCache);
    }

    public static void start(Scanner sc, DirFileCache dirFileCache) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(sc.nextLine());
            if (LOAD == userChoice) {
                System.out.println(FILENAME_LOAD);
                String key = sc.nextLine();
                dirFileCache.put(key, dirFileCache.get(key));
            } else if (GET == userChoice) {
                System.out.println(FILENAME_GET);
                System.out.println(dirFileCache.get(sc.nextLine()));
            } else {
                run = false;
                System.out.println("Конец работы");
            }
        }
    }
}