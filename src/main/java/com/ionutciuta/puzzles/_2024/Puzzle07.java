package com.ionutciuta.puzzles._2024;

import com.ionutciuta.data.PuzzleData;
import com.ionutciuta.puzzles.Puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Puzzle07 extends Puzzle<Long> {
    record Input(long t, List<Long> n) {}

    private final Function<String, Input> lineParser = line -> {
        var parts = line.split(":");
        var t = Long.parseLong(parts[0]);
        var n = Arrays.stream(parts[1].trim().split("\\s+")).map(Long::parseLong).toList();
        return new Input(t, n);
    };

    @Override
    public Long solvePart1(String inputFile) {
        var data = PuzzleData.getFromFile(inputFile, lineParser);
        var r = 0L;
        for (var line : data) {
            if (sumOrProd(line.t, line.n, 1, line.n.get(0))) r += line.t;
        }
        return r;
    }

    private boolean sumOrProd(long t, List<Long> n, int i, long acc) {
        if (acc == t) {
            return true;
        }

        if (i == n.size()) {
            return false;
        }

        return sumOrProd(t, n, i + 1, acc + n.get(i)) || sumOrProd(t, n, i + 1, acc * n.get(i));
    }

    @Override
    protected Long solvePart2(String inputFile) {
        var r = 0L;
        return r;
    }
}
