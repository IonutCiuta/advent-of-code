package com.ionutciuta.puzzles;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class Puzzle01 extends Puzzle<Integer> {
    @Override
    public Integer solvePart1(String inputFile) {
        final var file = new File(inputFile);
        var numbers = new ArrayList<Integer>();

        try (var it = FileUtils.lineIterator(file)) {
            while (it.hasNext()) {
                var firstNumber = 0;
                var lastNumber = 0;
                var firstNumberSet = false;
                var rawLine = it.nextLine();
                var line = rawLine.toCharArray();
                for (char c : line) {
                    if (c >= 48 && c <= 57) {
                        if (!firstNumberSet) {
                            firstNumber = c - 48;
                            lastNumber = c - 48;
                            firstNumberSet = true;
                        } else {
                            lastNumber = c - 48;
                        }
                    }
                }
                numbers.add(firstNumber * 10 + lastNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numbers.stream().reduce(Integer::sum).orElse(-1);
    }

    private String getNumber(String rawLine, int start, int len) {
        if (start + len < rawLine.length()) {
            return rawLine.substring(start, start + len);
        }
        return rawLine.substring(start);
    }

    @Override
    protected Integer solvePart2(String inputFile) {
        final var file = new File(inputFile);
        var numbers = new ArrayList<Integer>();

        try (var it = FileUtils.lineIterator(file)) {
            while (it.hasNext()) {
                var firstNumber = 0;
                var lastNumber = 0;
                var firstNumberSet = false;
                var rawLine = it.nextLine();
                var line = rawLine.toCharArray();
                for (int i = 0; i < line.length; i++) {
                    var c = line[i];
                    if (c >= 48 && c <= 57) {
                        if (!firstNumberSet) {
                            firstNumber = c - 48;
                            lastNumber = c - 48;
                            firstNumberSet = true;
                        } else {
                            lastNumber = c - 48;
                        }
                    } else {
                        // [eight, five, four, nine, one, seven, six, three, two, zero]
                        var textNumber = -1;

                        if (c == 'e' && "eight".equalsIgnoreCase(getNumber(rawLine, i, 5))) {
                            textNumber = 8;
                        } else if (c == 'f') {
                            if ("four".equals(getNumber(rawLine, i, 4))) {
                                textNumber = 4;
                            } else if ("five".equalsIgnoreCase(getNumber(rawLine, i, 4))) {
                                textNumber = 5;
                            }
                        } else if (c == 'n' && "nine".equalsIgnoreCase(getNumber(rawLine, i, 4))) {
                            textNumber = 9;
                        } else if (c == 'o' && "one".equalsIgnoreCase(getNumber(rawLine, i, 3))) {
                            textNumber = 1;
                        } else if (c == 's') {
                            if ("six".equalsIgnoreCase(getNumber(rawLine, i, 3))) {
                                textNumber = 6;
                            } else if ("seven".equalsIgnoreCase(getNumber(rawLine, i, 5))) {
                                textNumber = 7;
                            }
                        } else if (c == 't') {
                            if ("three".equalsIgnoreCase(getNumber(rawLine, i, 5))) {
                                textNumber = 3;
                            } else if ("two".equalsIgnoreCase(getNumber(rawLine, i, 3))) {
                                textNumber = 2;
                            }
                        } else if (c == 'z' && "zero".equalsIgnoreCase(getNumber(rawLine, i, 4))) {
                            textNumber = 0;
                        }

                        if (textNumber != -1) {
                            if (!firstNumberSet) {
                                firstNumber = textNumber;
                                lastNumber = textNumber;
                                firstNumberSet = true;
                            } else {
                                lastNumber = textNumber;
                            }
                        }
                    }
                }
                numbers.add(firstNumber * 10 + lastNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numbers.stream().reduce(Integer::sum).orElse(-1);
    }
}
