package com.ionutciuta.data;

import com.ionutciuta.parsing.FileToLinesParser;

import java.io.File;

public class CharMap {
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
