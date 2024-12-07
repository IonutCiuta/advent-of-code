package com.ionutciuta.puzzles._2024;

import com.ionutciuta.puzzles.Puzzle;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class Puzzle06 extends Puzzle<Integer> {

    @Override
    public Integer solvePart1(String inputFile) {
        final var file = new File(inputFile);

        var lines = new ArrayList<String>();

        try (var it = FileUtils.lineIterator(file)) {
            while (it.hasNext()) {
                lines.add(it.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        var map = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }

        int x = -1, y = -1;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (map[i][j] == '^') {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x > -1) {
                break;
            }
        }

        var dir = new int[][] {
                new int[] { -1,  0 },
                new int[] {  0,  1 },
                new int[] {  1,  0 },
                new int[] {  0, -1 },
        };
        int d = 0;
        int visited = 1 ;
        while (true) {
            int xx = x + dir[d][0];
            int yy = y + dir[d][1];

            if (map[xx][yy] == '#') {
                d = d == dir.length - 1 ? 0 : d + 1;
                continue;
            }

            if (map[xx][yy] != '^') {
                visited++;
                map[xx][yy] = '^';
            }

            if (xx == 0 || xx == map.length - 1 || yy == 0 || yy == map[0].length) {
                return visited;
            }

            x = xx;
            y = yy;
        }
    }

    @Override
    protected Integer solvePart2(String inputFile) {
        final var file = new File(inputFile);
        var result = -1;
        try (var it = FileUtils.lineIterator(file)) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
