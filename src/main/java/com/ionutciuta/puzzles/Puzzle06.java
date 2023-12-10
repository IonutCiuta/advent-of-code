package com.ionutciuta.puzzles;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Puzzle06 extends Puzzle<Integer> {
    private record Race(int duration, int recordDistance) {
    }

    @Override
    public Integer solvePart1(String inputFile) {
        final var file = new File(inputFile);

        var raceResults = new ArrayList<List<Integer>>();
        var races = getRaces(file);
        Log.debug(races);

        for (Race race : races) {
            var currentRaceResults = new ArrayList<Integer>();
            var limit = (race.duration() / 2) + 1;
            for (int j = 1; j < limit; j++) {
                var distance = j * (race.duration() - j);
                if (distance > race.recordDistance()) {
                    currentRaceResults.add(distance);
                }
            }

            Log.debug(currentRaceResults);
            raceResults.add(currentRaceResults);
        }

        Log.debug(raceResults);
        return raceResults.stream()
                // If list has even len, just double - else - double even part and add one for the middle number
                .map(l -> l.size() % 2 == 0 ? 2 * l.size() : (2 * (l.size() - 1)) + 1)
                .reduce((s1, s2) -> s1 * s2)
                .orElse(-1);
    }

    private List<Race> getRaces(File file) {
        try (var it = FileUtils.lineIterator(file)) {
            var times = splitLine(it.nextLine());
            var distances = splitLine(it.nextLine());
            var races = new ArrayList<Race>();
            for (int i = 0; i < times.size(); i++) {
                races.add(new Race(
                        Integer.parseInt(times.get(i)),
                        Integer.parseInt(distances.get(i))
                ));
            }
            return races;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private List<String> splitLine(String line) {
        return Arrays.stream(line.split(":")[1]
                        .trim().split(" "))
                .filter(s -> !s.isEmpty())
                .toList();
    }

    private record LongRace(long duration, long recordDistance) {
    }

    @Override
    protected Integer solvePart2(String inputFile) {
        final var file = new File(inputFile);

        var race = getRace(file);
        var raceResults = new ArrayList<Long>();

        var limit = (race.duration() / 2) + 1;
        for (int j = 1; j < limit; j++) {
            var distance = j * (race.duration() - j);
            if (distance > race.recordDistance()) {
                raceResults.add(distance);
            }
        }

        Log.debug(raceResults);
        return (2 * raceResults.size()) - 1;
    }

    private LongRace getRace(File file) {
        try (var it = FileUtils.lineIterator(file)) {
            var times = getNumber(it.nextLine());
            var distances = getNumber(it.nextLine());
            return new LongRace(times, distances);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private long getNumber(String line) {
        return Long.parseLong(Arrays.stream(line.split(":")[1].trim().split(" "))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining())
        );
    }
}
