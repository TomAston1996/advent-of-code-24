package dev.tomaston.day2;

import dev.tomaston.common.ChallengeConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayTwo {

    private final String ROOT_PATH;
    private final List<List<Integer>> reports;

    public DayTwo(ChallengeConfig config) {
        this.ROOT_PATH = config.getRootPath();
        this.reports = new ArrayList<>();
        parseInput();
    }

    public void firstChallenge() {
        int ans = 0;
        for (List<Integer> report : reports) {
            if (validateReport(report)) ans++;
        }
        System.out.println("D2C1 Answer: " + ans);
    }

    public void secondChallenge() {
        int ans = 0;
        for (List<Integer> report : reports) {
            if (validateReportsWithDampener(report)) ans++;
        }
        System.out.println("D2C2 Answer: " + ans);
    }

    /**
     * Read input file and parse reports/levels
     */
    private void parseInput() {
        try {
            String INPUT_FILE = "files/day2-input.txt";
            File inputFile = new File(ROOT_PATH + INPUT_FILE);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                reports.add(splitLineIntoList(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found...");
            throw new RuntimeException(e);
        }
    }

    private List<Integer> splitLineIntoList(String line) {
        List<Integer> numbers = new ArrayList<>();
        String[] levels = line.split("\\s+");
        for (String num : levels) {
            numbers.add(Integer.parseInt(num));
        }
        return numbers;
    }

    private boolean validateReport(List<Integer> report) {
        boolean isValidIncreasing = checkIncreasing(report);
        boolean isValidDecreasing = checkDecreasing(report);
        return isValidDecreasing || isValidIncreasing;
    }

    private  boolean validateReportsWithDampener(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            int temp = report.remove(i);

            boolean isValidIncreasing = checkIncreasing(report);
            boolean isValidDecreasing = checkDecreasing(report);

            if (isValidDecreasing || isValidIncreasing) return true;

            report.add(i, temp);
        }
        return false;
    }

    private boolean checkIncreasing(List<Integer> report) {
        int prev = report.getFirst();
        for (int i = 1; i < report.size(); i++) {
            int curr = report.get(i);
            boolean isBad = curr <= prev || Math.abs(curr - prev) > 3;
            if (isBad) return false;
            prev = curr;
        }
        return true;
    }

    private boolean checkDecreasing(List<Integer> report) {
        int prev = report.getFirst();
        for (int i = 1; i < report.size(); i++) {
            int curr = report.get(i);
            boolean isBad = curr >= prev || Math.abs(curr - prev) > 3;
            if (isBad) return false;
            prev = curr;
        }
        return true;
    }
}
