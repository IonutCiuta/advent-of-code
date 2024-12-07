package com.ionutciuta.puzzles._2023;

import com.ionutciuta.puzzles.Log;
import com.ionutciuta.puzzles.Puzzle;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle03 extends Puzzle<Integer> {
    @Override
    public Integer solvePart1(String inputFile) {
        final var file = new File(inputFile);
        final var schema = parseSchema(file);

        var numbers = schema.numbers();
        var symbols = schema.symbols();
        var result = new ArrayList<String>();

        for (var n : numbers.entrySet()) {
            var numericValue = n.getValue();
            var coord = n.getKey();

            var len = numericValue.length();
            var moveOn = false;
            for (int i = coord.x - 1; i <= coord.x + len; i++) {
                for (int j = -1; j <= 1; j++) {
                    var testCoord = new Coord(i, coord.y + j);
                    if (symbols.containsKey(testCoord)) {
                        Log.debug("Number %s @ %s has adjacent symbol @ %s.\n", numericValue, coord, testCoord);
                        result.add(numericValue);
                        moveOn = true;
                        break;
                    } else {
                        Log.debug("Number %s @ %s has NO adjacent symbol @ %s.\n", numericValue, coord, testCoord);
                    }
                }

                if (moveOn) {
                    break;
                }
            }
        }

        Log.debug(result);
        var sum = result.stream().map(Integer::parseInt).reduce(Integer::sum).orElse(-1);
        Log.debug("Sum: %d\n", sum);
        return sum;
    }

    @Override
    protected Integer solvePart2(String inputFile) {
        final var file = new File(inputFile);
        final var schema = parseSchema(file);

        var numbers = schema.numbers();
        var symbols = schema.symbols();

        var gears = new HashMap<Coord, List<Integer>>();

        for (var n : numbers.entrySet()) {
            var numericValue = n.getValue();
            var coord = n.getKey();

            var len = numericValue.length();
            var number = Integer.parseInt(numericValue);

            for (int i = coord.x - 1; i <= coord.x + len; i++) {
                for (int j = -1; j <= 1; j++) {
                    var testCoord = new Coord(i, coord.y + j);
                    var symbol = symbols.get(testCoord);

                    if (symbol != null && symbol == '*') {
                        Log.debug("Number %s @ %s is adjacent to gear @ %s.\n", numericValue, coord, testCoord);
                        var gearNumbers = gears.get(testCoord);
                        if (gearNumbers != null) {
                            gearNumbers.add(number);
                        } else {
                            var newGearNumbers = new ArrayList<Integer>();
                            newGearNumbers.add(number);
                            gears.put(testCoord, newGearNumbers);
                        }
                    }
                }
            }
        }

        Log.debug(gears);
        var gearRatio = gears.values().stream()
                .filter(ns -> ns.size() == 2)
                .map(ns -> ns.stream().reduce((n, acc) -> n * acc).orElse(0))
                .reduce(Integer::sum).orElse(-1);
        Log.debug("Gear ratio: %d\n", gearRatio);
        return gearRatio;
    }

    private record Coord(int x, int y) {
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    private record Schema(Map<Coord, String> numbers, Map<Coord, Character> symbols) {
    }

    Schema parseSchema(File file) {
        final var numbers = new HashMap<Coord, String>();
        final var symbols = new HashMap<Coord, Character>();

        var lineNumber = 0;
        try (var it = FileUtils.lineIterator(file)) {
            while (it.hasNext()) {
                var inputLine = it.nextLine().toCharArray();

                var number = "";
                for (int i = 0; i < inputLine.length; i++) {
                    var c = inputLine[i];
                    if (c >= '0' && c <= '9') {
                        number += c;
                    } else {
                        if (!number.isEmpty()) {
                            var coord = new Coord((i - number.length()), lineNumber);
                            Log.debug("# Found number %s at %s\n", number, coord);
                            numbers.put(coord, number);
                            number = "";
                        }

                        if (c != '.') {
                            var coord = new Coord(i, lineNumber);
                            Log.debug("* Found symbol %c at %s\n", c, coord);
                            symbols.put(coord, c);
                        }
                    }
                }

                if (!number.isEmpty()) {
                    var coord = new Coord(inputLine.length - number.length(), lineNumber);
                    numbers.put(coord, number);
                }

                lineNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Schema(numbers, symbols);
    }
}
