package com.ionutciuta.puzzles;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle02Test implements PuzzleTest {
    private final Puzzle02 puzzle = new Puzzle02();

    @Test
    public void helperFunctionsShouldActuallyHelp() {
        var gameLine = "Game 101: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        assertEquals(101, puzzle.getGameNumber(gameLine));

        assertEquals(
                List.of("8 green, 6 blue, 20 red", "5 blue, 4 red, 13 green", "5 green, 1 red"),
                puzzle.getGameSets(gameLine)
        );

        assertEquals(888, puzzle.getGreenNumber("888 green, 666 blue, 777 red"));
        assertEquals(777, puzzle.getRedNumber("888 green, 666 blue, 777 red"));
        assertEquals(666, puzzle.getBlueNumber("888 green, 666 blue, 777 red"));

        assertEquals(0, puzzle.getBlueNumber("888 green, 777 red"));
        assertEquals(0, puzzle.getGreenNumber("666 blue, 777 red"));
        assertEquals(0, puzzle.getRedNumber("888 green"));
    }

    @Test
    @Override
    public void shouldReturnExpectedTestResult_part1() {
        final var result = puzzle.solvePart1ForTestInput();
        assertEquals(8, result);
    }

    @Test
    @Override
    public void shouldReturnResult_part1() {
        final var result = puzzle.solvePart1();
        System.out.println(result);
        assertEquals(2105, result);
    }

    @Test
    @Override
    public void shouldReturnExpectedTestResult_part2() {
        final var result = puzzle.solvePart2ForTestInput();
        assertEquals(2286, result);
    }

    @Test
    @Override
    public void shouldReturnResult_part2() {
        final var result = puzzle.solvePart2();
        System.out.println(result);
        assertEquals(72422, result);
    }
}