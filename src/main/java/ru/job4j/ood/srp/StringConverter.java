package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class StringConverter {
    private String inputString;
    private int output;
    private static final Predicate<Integer> PRED = o -> o < 0;

    public StringConverter(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public int getOutput() {
        return output;
    }

    public int convertToInt() {
        try {
            output = Integer.parseInt(inputString);
        } catch (Exception e) {
            output = 0;
        }
        if (PRED.test(output)) {
            output = 0;
        }
        return output;
    }

    public void printOutput() {
        System.out.println(output);
    }

}
