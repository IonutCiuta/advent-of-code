package com.ionutciuta.puzzles._2024;

import com.ionutciuta.puzzles.PuzzleTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle07Test implements PuzzleTest {
    private final Puzzle07 puzzle = new Puzzle07();

    @Test
    @Override
    public void shouldReturnExpectedTestResult_part1() {
        final var result = puzzle.solvePart1ForTestInput();
        assertEquals(3749, result);
    }

    @Test
    @Override
    public void shouldReturnResult_part1() {
        final var result = puzzle.solvePart1();
        System.out.println(result);
        assertEquals(7885693428401L, result);
    }

    @Test
    @Override
    public void shouldReturnExpectedTestResult_part2() {
        final var result = puzzle.solvePart2ForTestInput();
        assertEquals(0, result);
    }

    @Test
    @Override
    public void shouldReturnResult_part2() {
        final var result = puzzle.solvePart2();
        System.out.println(result);
        assertEquals(0, result);
    }
}