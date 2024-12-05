package dev.tomaston.day3;

import dev.tomaston.common.ChallengeConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

public class DayThree {

    private final String ROOT_PATH;

    private ArrayList<String> lines;

    public DayThree(ChallengeConfig config) {
        this.ROOT_PATH = config.getRootPath();
        this.lines = new ArrayList<>();
        parseInput();
    }

    public void firstChallenge(){
        int ans = 0;
        boolean ignore = false;
        for (String line : lines) {
            String[] matchingStatements = findAllValidExpressions(line);
            for (String statement : matchingStatements) {
                System.out.println(statement);
                if (statement.equals("do()")) {
                    ignore = false;
                    System.out.println("not ignoring anymore");
                } else if (statement.equals("don't()")) {
                    ignore = true;
                    System.out.println("ignoring");
                }
                if (!ignore) {
                    List<String> numbers = findIntegers(statement);
                    if (numbers.size() != 2) continue;
                    ans += Integer.parseInt(numbers.get(0)) * Integer.parseInt(numbers.get(1));
                }
            }

            printMatchStrings(matchingStatements);
        }
        System.out.println("D3C1 Answer: " + ans);
    }

    private void parseInput() {
        try {
            String INPUT_FILE = "files/day3-input.txt";
            File inputFile = new File(ROOT_PATH + INPUT_FILE);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found...");
            throw new RuntimeException(e);
        }
    }

    private String[] findAllValidExpressions(String line) {
        return Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)")
                .matcher(line)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);
    }

    private void printMatchStrings(String[] matches) {
        for (String match : matches) {
            System.out.println(match);
        }
    }

    private List<String> findIntegers(String stringToSearch) {
        Pattern integerPattern = Pattern.compile("-?\\d+");
        Matcher matcher = integerPattern.matcher(stringToSearch);

        List<String> integerList = new ArrayList<>();
        while (matcher.find()) {
            integerList.add(matcher.group());
        }
        return integerList;
    }
}
