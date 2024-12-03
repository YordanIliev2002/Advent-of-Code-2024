package aoc.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 {
    public static void main() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/data/day2.txt"));
        List<String> lines = new ArrayList<>();
        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        List<List<Integer>> reports = lines.stream()
            .map(Day2::parseIntList)
            .toList();

        long safeReports = reports.stream()
            .filter(Day2::isSafe)
            .count();
        System.out.println(STR."Part 1: \{safeReports}");

        long almostSafeReports = reports.stream()
            .filter(Day2::isAlmostSafe)
            .count();
        System.out.println(STR."Part 2: \{almostSafeReports}");
    }

    private static List<Integer> parseIntList(String line) {
        return Arrays.stream(line.split(" "))
            .map(Integer::parseInt)
            .toList();
    }

    private static boolean isAlmostSafe(List<Integer> report) {
        return getVariations(report)
            .anyMatch(Day2::isSafe);
    }

    private static Stream<List<Integer>> getVariations(List<Integer> report) {
        Stream<List<Integer>> reportsWithSingleRemoval = IntStream.range(0, report.size())
            .mapToObj(idxToRemove -> {
                List<Integer> copy = new ArrayList<>(report);
                copy.remove(idxToRemove);
                return copy;
            });
        return Stream.concat(
            Stream.of(report),
            reportsWithSingleRemoval
        );
    }

    private static boolean isSafe(List<Integer> report) {
        for (int i = 0; i < report.size() - 2; i++) {
            if ((report.get(i) - report.get(i + 1)) * (report.get(i + 1) - report.get(i + 2)) < 0) {
                return false;
            }
        }
        for (int i = 0; i < report.size() - 1; i++) {
            int diff = Math.abs(report.get(i) - report.get(i + 1));
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }
}
