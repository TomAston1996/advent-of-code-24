package dev.tomaston.day6;

import dev.tomaston.common.ChallengeConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DaySix {

    private final String ROOT_PATH;

    private final List<List<Character>> map;
    private int startRow;
    private int startCol;
    Set<List<Integer>> visited;

    public DaySix(ChallengeConfig config) {
        this.ROOT_PATH = config.getRootPath();
        this.map = new ArrayList<>();
        this.visited = new HashSet<>();
        parseInput();
    }

    private void parseInput() {
        try {
            String INPUT_FILE = "files/day6-small-input.txt";
            File inputFile = new File(ROOT_PATH + INPUT_FILE);
            Scanner scanner = new Scanner(inputFile);
            int rowIndex = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<Character> row = new ArrayList<>(line.length());
                int colIndex = 0;
                for (char ch : line.toCharArray()) {
                    row.add(ch);
                    if (ch == '^') {
                        startRow = rowIndex;
                        startCol = colIndex;
                    }
                    colIndex++;
                }
                rowIndex++;
                map.add(row);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found...");
            throw new RuntimeException(e);
        }
    }

    public void firstChallenge() {
        helper(startRow, startCol, 0);
        System.out.println("D6C1 Answer: " + visited.size());
    }

    private void helper(int row, int col, int direction) {
        if (row < 0 || row > map.size() - 1) return;
        if (col < 0 || col > map.get(row).size() - 1) return;

        int[][] coordinates = new int[][] {
                {-1, 0}, //up
                {0, 1}, //right
                {1, 0}, //down
                {0, -1} //left
        };

        if (map.get(row).get(col) == '#') {
            int nextDirection = calculateNextDirection(direction);
            if (coordinates[direction][0] != 0) {
                //if we were going up or down - we need to come back on ourselves
                helper(row + (coordinates[direction][0] * -1), col + coordinates[nextDirection][1], nextDirection);
            } else {
                // if we were going left or right - we need to come back on ourselves
                helper(row + coordinates[nextDirection][0], col + (coordinates[direction][1] * -1), nextDirection);
            }
        } else {
            visited.add(new ArrayList<>(Arrays.asList(row, col)));
            map.get(row).set(col, 'X');
            helper(row + coordinates[direction][0], col + coordinates[direction][1], direction);
        }
    }

    //0 up, 1 right, 2 down, 3 left
    private int calculateNextDirection(int direction) {
        assert(direction >= 0 && direction <= 3);
        return direction + 1 > 3 ? 0 : direction + 1;
    }

    private void printMap() {
        for (List<Character> row : map) {
            for (Character ch : row) {
                System.out.print(ch + "\t");
            }
            System.out.println();
        }
    }
}
