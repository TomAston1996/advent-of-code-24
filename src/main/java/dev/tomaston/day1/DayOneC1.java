package dev.tomaston.day1;

import dev.tomaston.common.ChallengeConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DayOneC1 {

    private final String ROOT_PATH;

    private final PriorityQueue<Integer> minHeapLeft;
    private final PriorityQueue<Integer> minHeapRight;

    /**
     * @param config challenge config
     */
    public DayOneC1(ChallengeConfig config) {
        this.ROOT_PATH = config.getRootPath();
        this.minHeapLeft = new PriorityQueue<>();
        this.minHeapRight = new PriorityQueue<>();
    }

    /**
     * First challenge
     */
    public void FirstChallenge() {
        parseInputToMinHeaps();
        int ans = calculateTotalDistance();
        System.out.println("Day One Challenge One Answer: " + ans);
    }

    /**
     * Read input file and parse left and right hand columns into left and right side min heaps
     */
    private void parseInputToMinHeaps() {
        try {
            String INPUT_FILE = "files/day1-input.txt";
            File inputFile = new File(ROOT_PATH + INPUT_FILE);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int[] numbers = splitLineIntoLeftAndRightSide(line);
                minHeapLeft.add(numbers[0]);
                minHeapRight.add(numbers[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found...");
            throw new RuntimeException(e);
        }
    }

    /**
     * @param line string line from scanner
     * @return array of size 2 with index 0 being left and index 1 being right
     */
    private int[] splitLineIntoLeftAndRightSide(String line) {
        String[] numbers = line.split("\\s+");
        assert(numbers.length == 2);
        return new int[] {Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])};
    }

    private int calculateDistanceBetweenLeftAndRight(Integer left, Integer right) {
        return Math.abs(left - right);
    }

    private int calculateTotalDistance() {
        int ans = 0;
        while (!minHeapLeft.isEmpty() && !minHeapRight.isEmpty()) {
            int left = minHeapLeft.remove();
            int right = minHeapRight.remove();
            ans += calculateDistanceBetweenLeftAndRight(left, right);
        }
        return ans;
    }
}
