package dev.tomaston.day5;

import dev.tomaston.common.ChallengeConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayFive {

    private final String ROOT_PATH;

    Map<Integer, List<Integer>> map;
    int ans = 0;

    public DayFive(ChallengeConfig config) {
        this.ROOT_PATH = config.getRootPath();
        this.map = new HashMap<>();
        parseInput();
    }

    public void firstChallenge() {
        System.out.println("D5C1 Answer: " + ans);
    }

    private void parseInput() {
        try {
            String INPUT_FILE = "files/day5-input.txt";
            File inputFile = new File(ROOT_PATH + INPUT_FILE);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("|")) {
                    buildMap(line);
                } else if (line.length() > 1) {
                    if (isValid(line)) {
                        int middleNumber = getMiddle(line);
                        ans += middleNumber;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found...");
            throw new RuntimeException(e);
        }
    }

    /**
     * @param rule in the format X|Y
     */
    private void buildMap(String rule) {
        String[] numbers = rule.split("\\|");
        assert(numbers.length == 2);

        //page X must occur before page Y
        int pageX = Integer.parseInt(numbers[0]);
        int pageY = Integer.parseInt(numbers[1]);

        if (map.containsKey(pageX)) {
            map.get(pageX).add(pageY);
        } else {
            map.put(pageX, new ArrayList<>(List.of(pageY)));
        }
    }

    private boolean isValid(String line) {
        int[] numbers = getListNumbers(line);
        Set<Integer> set = new HashSet<>();
        for (int number : numbers) {
            List<Integer> notAllowed = map.get(number);
            for (int disallowedNumber : notAllowed) {
                if (set.contains(disallowedNumber)) {
                    return false;
                }
            }
            set.add(number);
        }
        return true;
    }

    private int[] getListNumbers(String line) {
        String[] numbers = line.split(",");
        int[] response = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            response[i] = Integer.parseInt(numbers[i]);
        }
        return response;
    }

    private int getMiddle(String line) {
        int[] numbers = getListNumbers(line);
        int mid =  numbers.length / 2;
        return numbers[mid];
    }
}
