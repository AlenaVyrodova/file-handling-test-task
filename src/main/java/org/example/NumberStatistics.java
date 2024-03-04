package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberStatistics {
    public static void main(String[] args) {
        String file = "src/main/java/org/example/10m.txt";

        List<Integer> numbers = readNumbersFromFile(file);

        if (!numbers.isEmpty()) {
            int maxValue = findMaxValue(numbers);
            int minValue = findMinValue(numbers);
            double median = findMedian(numbers);
            double average = findAverage(numbers);
            List<Integer> increasingSequence = findIncreasingSequence(numbers);
            List<Integer> decreasingSequence = findDecreasingSequence(numbers);

            System.out.println("The maximum number is  " + maxValue);
            System.out.println("The minimum number is  " + minValue);
            System.out.println("Median  " + median);
            System.out.println("Average number " + average);
            System.out.println("Increasing sequence is " + increasingSequence);
            System.out.println("Decreasing sequence is" + decreasingSequence);
        } else {
            System.out.println("The file is empty");
        }
    }

    private static List<Integer> readNumbersFromFile(String file) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lineOfNumber;
            while ((lineOfNumber = reader.readLine()) != null) {
                int number = Integer.parseInt(lineOfNumber.trim());
                numbers.add(number);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file ", e);
        }
        return numbers;
    }

    private static int findMaxValue(List<Integer> numbers) {
        return Collections.max(numbers);
    }

    private static int findMinValue(List<Integer> numbers) {
        return Collections.min(numbers);
    }

    private static double findMedian(List<Integer> numbers) {
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .toList();
        int size = sortedNumbers.size();
        return (size % 2 == 0) ? (double) (sortedNumbers.get(size / 2 - 1)
                + sortedNumbers.get(size / 2)) / 2
                : sortedNumbers.get(size / 2);
    }

    private static double findAverage(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (double) sum / numbers.size();

    }

    private static List<Integer> findIncreasingSequence(List<Integer> numbers) {
        List<Integer> incSequence = new ArrayList<>();
        List<Integer> currentSequence = new ArrayList<>();

        if (numbers.isEmpty()) {
            return incSequence;
        }

        currentSequence.add(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > numbers.get(i - 1)) {
                currentSequence.add(numbers.get(i));
            } else {
                if (currentSequence.size() > incSequence.size()) {
                    incSequence = new ArrayList<>(currentSequence);
                }
                currentSequence.clear();
                currentSequence.add(numbers.get(i));
            }
        }
        if (currentSequence.size() > incSequence.size()) {
            incSequence = new ArrayList<>(currentSequence);
        }

        return incSequence;
    }

    private static List<Integer> findDecreasingSequence(List<Integer> numbers) {
        List<Integer> decrSequence = new ArrayList<>();
        List<Integer> currentSequence = new ArrayList<>();

        if (numbers.isEmpty()) {
            return decrSequence;
        }
        currentSequence.add(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) < numbers.get(i - 1)) {
                currentSequence.add(numbers.get(i));
            } else {
                if (currentSequence.size() > decrSequence.size()) {
                    decrSequence = new ArrayList<>(currentSequence);
                }
                currentSequence.clear();
                currentSequence.add(numbers.get(i));
            }
        }

        if (currentSequence.size() > decrSequence.size()) {
            decrSequence = new ArrayList<>(currentSequence);
        }

        return decrSequence;
    }
}

