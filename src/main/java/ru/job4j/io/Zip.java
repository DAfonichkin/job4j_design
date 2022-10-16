package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName argsName) {
        Path dir = Paths.get(argsName.get("d"));
        if ((!Files.isDirectory(dir))) {
            throw new IllegalArgumentException(String.format("%s is not a directory", argsName.get("d")));
        }
        String extensions = argsName.get("e");
        if (extensions.indexOf(".") != 0) {
            throw new IllegalArgumentException(String.format("Wrong format of extension - %s", argsName.get("e")));
        }
        String target = argsName.get("o");
        if (!target.endsWith(".zip")) {
            throw new IllegalArgumentException("Output file should have .zip extension");
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Wrong amounts of parameters");
        }
        ArgsName zipParams = ArgsName.of(args);
        Zip zip = new Zip();
        zip.validate(zipParams);
        zip.packFiles(
                Search.search(Paths.get(zipParams.get("d")), p -> !p.toFile().getName().endsWith(zipParams.get("e"))),
                Paths.get(zipParams.get("o")));
    }
}