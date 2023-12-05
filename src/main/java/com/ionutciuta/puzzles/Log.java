package com.ionutciuta.puzzles;

public final class Log {
    private static final String ENV_VAR_NAME = "DEBUG";

    public static void debug(String msg, Object... args) {
        if (Boolean.parseBoolean(System.getenv(ENV_VAR_NAME))) {
            System.out.printf(msg, args);
        }
    }

    public static void debug(Object in) {
        if (Boolean.parseBoolean(System.getenv(ENV_VAR_NAME))) {
            System.out.println(in);
        }
    }
}
