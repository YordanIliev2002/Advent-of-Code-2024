package aoc.days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    private static final Pattern PATTERN = Pattern.compile("(mul\\((?<first>\\d{1,3}),(?<second>\\d{1,3})\\)|don't\\(\\)|do\\(\\))");

    public static void main() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/data/day3.txt"));
        String line = scanner.nextLine();
        int part1 = getSum(line, false);
        System.out.println(STR."Part 1: \{part1}");
        int part2 = getSum(line, true);
        System.out.println(STR."Part 2: \{part2}");
    }

    private static int getSum(String line, boolean isTogglingEnabled) {
        Matcher matcher = PATTERN.matcher(line);
        int result = 0;
        boolean isMultiplicationEnabled = true;
        while (matcher.find()) {
            switch (matcher.group()) {
                case "don't()" -> {
                    if (isTogglingEnabled) {
                        isMultiplicationEnabled = false;
                    }
                }
                case "do()" -> {
                    if (isTogglingEnabled) {
                        isMultiplicationEnabled = true;
                    }
                }
                default -> {
                    if (isMultiplicationEnabled) {
                        int first = Integer.parseInt(matcher.group("first"));
                        int second = Integer.parseInt(matcher.group("second"));
                        result += first * second;
                    }
                }
            }
        }
        return result;
    }
}
