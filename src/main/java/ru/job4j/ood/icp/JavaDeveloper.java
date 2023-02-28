package ru.job4j.ood.icp;

public class JavaDeveloper implements Developer {
    @Override
    public void programmingOnC() {
        throw new IllegalStateException("Java programmer can't programming on C");
    }

    @Override
    public void programmingOnJava() {
        System.out.println("Programming");
    }
}
