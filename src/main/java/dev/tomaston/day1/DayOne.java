package dev.tomaston.day1;

import dev.tomaston.common.ChallengeConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayOne {

    private final String ROOT_PATH;

    //1st challenge vars
    private final PriorityQueue<Integer> minHeapLeft;
    private final PriorityQueue<Integer> minHeapRight;

    //2nd challenge vars
    private final HashMap<Integer, Integer> counts;
    private ArrayList<Integer> secondListNumbers;

    /**
     * @param config challenge config
     */
    public DayOne(ChallengeConfig config) {
        this.ROOT_PATH = config.getRootPath();
        this.minHeapLeft = new PriorityQueue<>();
        this.minHeapRight = new PriorityQueue<>();
        this.counts = new HashMap<>();
        this.secondListNumbers = new ArrayList<>();
        parseInput();
    }

    /**
     * Day One First challenge
     */
    public void firstChallenge() {
        System.out.println("D1C1 Answer: " + calculateTotalDistance());
    }

    /**
     * Day One Second Challenge
     */
    public void secondChallenge() {
        updateCountsMap();
        System.out.println("D1C2 Answer: " + calculateSimilarityScore());
    }

    /**
     * Read input file and parse left and right hand columns into left and right side min heaps
     */
    private void parseInput() {
        try {
            String INPUT_FILE = "files/day1-input.txt";
            File inputFile = new File(ROOT_PATH + INPUT_FILE);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int[] numbers = splitLineIntoLeftAndRightSide(line);

                //add left and right columns to min heaps -> 1st challenge
                minHeapLeft.add(numbers[0]);
                minHeapRight.add(numbers[1]);

                //initialise count map to zero for every item in the second list -> 2nd challenge
                counts.put(numbers[0], 0);
                secondListNumbers.add(numbers[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found...");
            throw new RuntimeException(e);
        }
    }

    /**
     * 1st Challenge Method
     * @param line string line from scanner
     * @return array of size 2 with index 0 being left and index 1 being right
     */
    private int[] splitLineIntoLeftAndRightSide(String line) {
        String[] numbers = line.split("\\s+");
        assert(numbers.length == 2);
        return new int[] {Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])};
    }

    /**
     * 1st Challenge Method
     * @param left left col num
     * @param right right col num
     * @return diff
     */
    private int calculateDistanceBetweenLeftAndRight(Integer left, Integer right) {
        return Math.abs(left - right);
    }

    /**
     * 1st Challenge Method
     * calculate total distance between left and right cols
     * @return ans
     */
    private int calculateTotalDistance() {
        int ans = 0;
        while (!minHeapLeft.isEmpty() && !minHeapRight.isEmpty()) {
            int left = minHeapLeft.remove();
            int right = minHeapRight.remove();
            ans += calculateDistanceBetweenLeftAndRight(left, right);
        }
        return ans;
    }

    /**
     * 2nd Challenge Method
     * Update the counts map based on numbers in the right hand list
     */
    private void updateCountsMap() {
        for (Integer num : secondListNumbers) {
            if (counts.containsKey(num)) {
                counts.put(num, counts.get(num) + 1);
            }
        }
    }

    private int calculateSimilarityScore() {
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int product = entry.getKey() * entry.getValue();
            ans += product;
        }
        return ans;
    }


}
