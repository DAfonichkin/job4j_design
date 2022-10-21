package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        long id = 2323;
        short height = 190;
        byte countOfChildren = 3;
        boolean isProgrammer = true;
        char gender = 'm';
        float yearsOfPractice = 5.5f;
        double accountBalance = 120394.99;
        LOG.debug("User info name : {}, age : {}, id : {}, height : {}, countOfChildren : {},"
                + " isProgrammer : {}, gender : {}, yearsOfPractice : {}, accountBalance : {}",
                name, age, id, height, countOfChildren, isProgrammer, gender, yearsOfPractice, accountBalance);
    }
}