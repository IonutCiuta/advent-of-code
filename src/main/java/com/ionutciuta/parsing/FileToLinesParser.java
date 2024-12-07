package com.ionutciuta.parsing;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileToLinesParser implements FileParser<List<String>> {
    private static final FileToLinesParser instance = new FileToLinesParser();

    private FileToLinesParser() {

    }

    public static FileToLinesParser getInstance() {
        return instance;
    }

    @Override
    public List<String> parseFile(File file) {
        var lines = new ArrayList<String>();
        try (var it = FileUtils.lineIterator(file)) {
            while (it.hasNext()) {
                lines.add(it.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}