package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<Path> duplicateFiles = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Map<FileProperty, Path> pathMap = new HashMap<>();
        Path duplicateFile = pathMap.put(new FileProperty(Files.size(file), file.getFileName().toString()), file);
        if (!(duplicateFile == null)) {
            duplicateFiles.add(file);
            duplicateFiles.add(duplicateFile);
        }
        return super.visitFile(file, attrs);
    }

    public void outputDuplicates() {
        for (Path path : duplicateFiles) {
            System.out.println(path.toAbsolutePath());
        }
    }
}