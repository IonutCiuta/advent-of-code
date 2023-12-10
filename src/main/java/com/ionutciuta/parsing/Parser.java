package com.ionutciuta.parsing;

import com.ionutciuta.datastructures.Matrix;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class Parser {
    public static <T> Matrix<T> parseMatrix(File file, Function<Character, T> transformer) {
        try (var it = FileUtils.lineIterator(file)) {
            var data = new ArrayList<List<T>>();
            while (it.hasNext()) {
                var chars = it.nextLine().toCharArray();
                var line = new ArrayList<T>();
                for (char c : chars) {
                    line.add(transformer.apply(c));
                }
                data.add(line);
            }
            return new Matrix<>(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
