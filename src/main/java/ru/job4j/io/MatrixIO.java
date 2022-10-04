package ru.job4j.io;

import java.io.FileOutputStream;

public class MatrixIO {

    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                array[row][col] = (row + 1) * (col + 1);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] array = multiple(5);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    out.write(((array[i][j]) + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}