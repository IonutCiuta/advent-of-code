package com.ionutciuta.data;

import com.ionutciuta.parsing.FileToLinesParser;

import java.io.File;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputList {
    public static <T> List<T> getFromFile(String fileName, Function<String, T> lineParser) {
        final var file = new File(fileName);
        return FileToLinesParser.getInstance().parseFile(file).stream()
                .map(lineParser).collect(Collectors.toList());
    }
}
