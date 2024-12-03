package aoc.days;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Day1 {
    public static void main() throws IOException {
        Scanner scanner = new Scanner(new File("src/main/resources/data/day1.txt"));
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        while (scanner.hasNextInt()) {
            first.add(scanner.nextInt());
            second.add(scanner.nextInt());
        }
        System.out.println(STR."Part1: \{part1(first, second)}");
        System.out.println(STR."Part2: \{part2(first, second)}");
    }

    private static int part1(List<Integer> first, List<Integer> second) {
        List<Integer> firstSorted = first.stream().sorted().toList();
        List<Integer> secondSorted = second.stream().sorted().toList();

        return IntStream.range(0, first.size())
            .map(idx -> Math.abs(firstSorted.get(idx) - secondSorted.get(idx)))
            .sum();
    }

    private static int part2(List<Integer> first, List<Integer> second) {
        Map<Integer, Integer> histogramSecond = second.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(i -> 1)));

        return first.stream()
            .mapToInt(number -> number * histogramSecond.getOrDefault(number, 0))
            .sum();
    }
}
