package com.ionutciuta.puzzles;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Puzzle02 extends Puzzle<Integer> {
    public static final int BLUE = 14;
    public static final int GREEN = 13;
    public static final int RED = 12;

    private static final Pattern gamePattern = Pattern.compile("Game ([0-9]+).*");
    private static final Pattern greenPattern = Pattern.compile("([0-9]+) green");
    private static final Pattern bluePattern = Pattern.compile("([0-9]+) blue");
    private static final Pattern redPattern = Pattern.compile("([0-9]+) red");

    @Override
    public Integer solvePart1(String inputFile) {
        final var file = new File(inputFile);

        var possibleGames = new ArrayList<Integer>();

        try (var it = FileUtils.lineIterator(file)) {
            while (it.hasNext()) {
                var line = it.nextLine();
                var gameNumber = getGameNumber(line);
                var gameSets = getGameSets(line);

                var possible = true;
                for (var set : gameSets) {
                    if (RED < getRedNumber(set) || GREEN < getGreenNumber(set) || BLUE < getBlueNumber(set)) {
                        possible = false;
                        break;
                    }
                }

                if (possible) {
                    possibleGames.add(gameNumber);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(possibleGames);
        return possibleGames
                .stream()
                .reduce(Integer::sum)
                .orElse(-1);
    }

    int getGameNumber(String line) {
        var matcher = gamePattern.matcher(line);
        if (matcher.find()) return Integer.parseInt(matcher.group(1));
        throw new IllegalArgumentException("Not a valid game line!");
    }

    List<String> getGameSets(String line) {
        var rawSets = line.split(": ")[1];
        return Arrays.stream(rawSets.split(";")).map(String::trim).toList();
    }

    int getGreenNumber(String line) {
        var matcher = greenPattern.matcher(line);
        if (matcher.find()) return Integer.parseInt(matcher.group(1));
        return 0;
    }

    int getRedNumber(String line) {
        var matcher = redPattern.matcher(line);
        if (matcher.find()) return Integer.parseInt(matcher.group(1));
        return 0;
    }

    int getBlueNumber(String line) {
        var matcher = bluePattern.matcher(line);
        if (matcher.find()) return Integer.parseInt(matcher.group(1));
        return 0;
    }

    @Override
    protected Integer solvePart2(String inputFile) {
        final var file = new File(inputFile);
        var powers = new ArrayList<Integer>();

        try (var it = FileUtils.lineIterator(file)) {
            while (it.hasNext()) {
                var line = it.nextLine();
                var gameSets = getGameSets(line);

                var maxRed = 0;
                var maxGreen = 0;
                var maxBlue = 0;

                for (var set : gameSets) {
                    maxRed = Math.max(maxRed, getRedNumber(set));
                    maxGreen = Math.max(maxGreen, getGreenNumber(set));
                    maxBlue = Math.max(maxBlue, getBlueNumber(set));
                }

                powers.add(maxRed * maxGreen * maxBlue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return powers
                .stream()
                .reduce(Integer::sum)
                .orElse(-1);
    }
}
