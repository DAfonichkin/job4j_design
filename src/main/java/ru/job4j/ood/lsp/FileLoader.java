package ru.job4j.ood.lsp;

public class FileLoader {
    public void load(int fileSize) {
        if (fileSize > 1000) {
            throw new IllegalArgumentException("File is too big");
        }
    }
}
