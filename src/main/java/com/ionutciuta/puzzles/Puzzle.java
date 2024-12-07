package com.ionutciuta.puzzles;

public abstract class Puzzle<R> {
    private static final String INPUT_FILE_TEMPLATE = "in/%s/%s.in";
    private static final String TEST_INPUT_FILE_TEMPLATE = "in/%s/%s.test.in";

    protected final String inputFilePath;
    protected final String testInputFilePath;

    public Puzzle() {
        this.inputFilePath = getInputFilePath();
        this.testInputFilePath = getTestInputFilePath();
    }

    protected abstract R solvePart1(String inputFile);

    public R solvePart1ForTestInput() {
        return solvePart1(testInputFilePath);
    }

    public R solvePart1() {
        return solvePart1(inputFilePath);
    }

    protected abstract R solvePart2(String inputFile);

    public R solvePart2ForTestInput() {
        return solvePart2(testInputFilePath);
    }

    public R solvePart2() {
        return solvePart2(inputFilePath);
    }

    private String getYear() {
        var packageTokens = this.getClass().getPackageName().split("\\.");
        return packageTokens[packageTokens.length - 1];
    }

    private String getPuzzleName() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    private String getInputFilePath() {
        return INPUT_FILE_TEMPLATE.formatted(getYear(), getPuzzleName());
    }

    private String getTestInputFilePath() {
        return TEST_INPUT_FILE_TEMPLATE.formatted(getYear(), getPuzzleName());
    }
}
