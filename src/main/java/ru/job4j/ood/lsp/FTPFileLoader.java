package ru.job4j.ood.lsp;

public class FTPFileLoader extends FileLoader {
    @Override
    public void load(int fileSize) {
        if (fileSize > 5000) {
            throw new IllegalArgumentException("File is too big");
        }
    }
}
