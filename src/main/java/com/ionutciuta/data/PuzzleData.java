package com.ionutciuta.data;

import com.ionutciuta.parsing.FileToLinesParser;

import java.io.File;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class PuzzleData {
    private PuzzleData() {}

    public static <T> List<T> getFromFile(String fileName, Function<String, T> lineParser) {
        final var file = new File(fileName);
        return FileToLinesParser.getInstance().parseFile(file).stream()
                .map(lineParser).collect(Collectors.toList());
    }

    public static char[][] getFromFile(String fileName) {
        final var file = new File(fileName);
        final var lines = FileToLinesParser.getInstance().parseFile(file);

        final var map = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }

        return map;
    }
}
