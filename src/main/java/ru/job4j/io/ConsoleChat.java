package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean run = true;
        boolean chatIsStopped = false;
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        while (run) {
            Scanner scanner = new Scanner(System.in);
            String question = scanner.nextLine();
            log.add(question);
            if (STOP.equals(question)) {
                chatIsStopped = true;
            }
            if (CONTINUE.equals(question)) {
                chatIsStopped = false;
            }
            run = !OUT.equals(question);
            if (!chatIsStopped && run) {
                String answer = phrases.get(new Random().nextInt(phrases.size() - 1));
                log.add(answer);
                System.out.println(answer);
            }
       }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> result = null;
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            result = in.lines()
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(botAnswers)
                ))) {
            for (String el : log) {
                out.println(el);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("answers.txt", "log.txt");
        cc.run();
    }
}