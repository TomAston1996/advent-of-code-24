package dev.tomaston.day4;

import dev.tomaston.common.ChallengeConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayFour {

    private final String ROOT_PATH;

    List<List<Character>> matrix;

    public DayFour(ChallengeConfig config) {
        this.ROOT_PATH = config.getRootPath();
        this.matrix = new ArrayList<>();
        parseInput();
    }

    private void parseInput() {
        try {
            String INPUT_FILE = "files/day4-input.txt";
            File inputFile = new File(ROOT_PATH + INPUT_FILE);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<Character> row = new ArrayList<>();
                for (Character ch : line.toCharArray()) {
                    row.add(ch);
                }
                matrix.add(row);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found...");
            throw new RuntimeException(e);
        }
    }

    public void firstChallenge() {
        int total = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) != 'X') continue;
                total += checkAllDirections(i, j);
            }
        }
        System.out.println("D4C1 Answer: " + total);
    }

    public void secondChallenge() {
        int total = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) != 'A') continue;
                total += checkXMasDirections(i, j) ? 1 : 0;
            }
        }
        System.out.println("D4C2 Answer: " + total);
    }
    
    private int checkAllDirections(int row, int col) {
        int total = 0;
        
        String wordToFind = "XMAS";
        //up
        total += checkDirection(row, col, 1, 0, 0, wordToFind) ? 1 : 0;
        
        //down
        total += checkDirection(row, col, -1, 0, 0, wordToFind) ? 1 : 0;
        
        //left
        total += checkDirection(row, col, 0, -1, 0, wordToFind) ? 1 : 0;
        
        //right
        total += checkDirection(row, col, 0, 1, 0, wordToFind) ? 1 : 0;

        //diagonal up left
        total += checkDirection(row, col, -1, -1, 0, wordToFind) ? 1 : 0;

        //diagonal up right
        total += checkDirection(row, col, -1, 1, 0, wordToFind) ? 1 : 0;

        //diagonal down left
        total += checkDirection(row, col, 1, -1, 0, wordToFind) ? 1 : 0;

        //diagonal down right
        total += checkDirection(row, col, 1, 1, 0, wordToFind) ? 1 : 0;

        return total;
    }
    
    private boolean checkDirection(int row, int col, int rowAdd, int colAdd, int index, String word) {
        if (row > matrix.size() - 1 || row < 0) return false;
        if (col > matrix.get(row).size() - 1 || col < 0) return false;
        if (matrix.get(row).get(col) != word.charAt(index)) return false;
        if (index == word.length() - 1) return true;
        return checkDirection(row + rowAdd, col + colAdd, rowAdd, colAdd, index + 1, word);
    }

    private boolean checkXMasDirections(int row, int col) {
        if (row - 1 < 0 || row + 1 > matrix.size() - 1) return false;
        if (col - 1 < 0 || col + 1 > matrix.get(row).size() - 1) return false;

        //check left diagonal
        boolean leftOpt1 = matrix.get(row - 1).get(col - 1) == 'M' && matrix.get(row + 1).get(col + 1) == 'S';
        boolean leftOpt2 = matrix.get(row - 1).get(col - 1) == 'S' && matrix.get(row + 1).get(col + 1) == 'M';

        //check right diagonal
        boolean rightOpt1 = matrix.get(row + 1).get(col - 1) == 'M' && matrix.get(row - 1).get(col + 1) == 'S';
        boolean rightOpt2 = matrix.get(row + 1).get(col - 1) == 'S' && matrix.get(row - 1).get(col + 1) == 'M';

        return (leftOpt1 || leftOpt2) && (rightOpt1 || rightOpt2);
    }
}
