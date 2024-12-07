package com.ionutciuta.puzzles._2023;

import com.ionutciuta.puzzles.Puzzle;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Puzzle04 extends Puzzle<Integer> {

    @Override
    public Integer solvePart1(String inputFile) {
        final var file = new File(inputFile);
        var score = 0;
        try (var it = FileUtils.lineIterator(file)) {
            while (it.hasNext()) {
                var line = it.nextLine();
                var cards = line.split(":")[1].split("\\|");

                var winningNumbers = Arrays.stream(cards[0].trim().split(" "))
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toSet());

                var myWinningNumbers = Arrays.stream(cards[1].trim().split(" "))
                        .filter(winningNumbers::contains)
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());

                // System.out.println(myWinningNumbers);

                if (!myWinningNumbers.isEmpty()) {
                    score += (int) Math.pow(2, myWinningNumbers.size() - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return score;
    }

    @Override
    protected Integer solvePart2(String inputFile) {
        final var file = new File(inputFile);
        var cardWinningNumbers = new HashMap<Integer, Integer>();
        var cardInstances = new HashMap<Integer, Integer>();

        var cardNumber = 0;
        try (var it = FileUtils.lineIterator(file)) {
            while (it.hasNext()) {
                cardNumber++;

                var line = it.nextLine();
                var cards = line.split(":")[1].split("\\|");

                var winningNumbers = Arrays.stream(cards[0].trim().split(" "))
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toSet());

                var myWinningNumbers = Arrays.stream(cards[1].trim().split(" "))
                        .filter(winningNumbers::contains)
                        .collect(Collectors.toSet());

                cardWinningNumbers.put(cardNumber ,myWinningNumbers.size());
                cardInstances.put(cardNumber, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        var maxCard = cardInstances.size();

        for (var cardWins : cardWinningNumbers.entrySet()) {
            var number = cardWins.getKey();
            var instances = cardInstances.get(number);
            // System.out.printf("card %d has %s instances %d wins\n", number, instances, wins);
            if (cardWins.getValue() > 0) {
                for (int i = 1; i <= cardWins.getValue(); i++) {
                    var nextCard = number + i;
                    var nextCardInstances = instances + cardInstances.get(nextCard);
                    // System.out.printf("\tcard %d add %d instance of card %d\n", number, nextCardInstances, nextCard);
                    if (nextCard <= maxCard) {
                        cardInstances.put(nextCard,  nextCardInstances);
                    }
                }
            }
        }

        // System.out.println(cardInstances);
        return cardInstances.values().stream().reduce(Integer::sum).orElse(-1);
    }
}
