package ru.job4j.ood.lsp;

public class FileLoader {
    private String driveLetter;

    public FileLoader(String driveLetter) {
        if (driveLetter.length() != 1) {
            throw new IllegalArgumentException("Drive letter not correct");
        }
        this.driveLetter = driveLetter;
    }

    public void setDriveLetter(String driveLetter) {
        if (driveLetter.length() != 1) {
            throw new IllegalArgumentException("Drive letter not correct");
        }
        this.driveLetter = driveLetter;
    }

    public void load(int fileSize) {
        if (fileSize > 1000) {
            throw new IllegalArgumentException("File is too big");
        }
    }


}
