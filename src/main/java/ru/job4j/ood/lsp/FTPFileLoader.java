package ru.job4j.ood.lsp;

public class FTPFileLoader extends FileLoader {
    private String driveLetter;
    public FTPFileLoader(String driveLetter) {
        super(driveLetter);
    }

    public void setDriveLetter(String driveLetter) {
        this.driveLetter = driveLetter;
    }

    @Override
    public void load(int fileSize) {
        if (fileSize > 5000) {
            throw new IllegalArgumentException("File is too big");
        }
    }
}
