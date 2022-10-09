package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader log = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            String status;
            String previousStatus = "";
            StringJoiner resultLine = new StringJoiner(";");
            while ((line = log.readLine()) != null) {
                status = line.substring(0, 3);
                if (statusIsUnavailable(status) && !statusIsUnavailable(previousStatus)) {
                    resultLine.add(line.substring(4));
                }
                if (!statusIsUnavailable(status) && statusIsUnavailable(previousStatus)) {
                    resultLine.add(line.substring(4));
                    out.println(resultLine);
                    resultLine = new StringJoiner(";");
                }
                previousStatus = status;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean statusIsUnavailable(String status) {
        return "400".equals(status) || "500".equals(status);
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:33");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}