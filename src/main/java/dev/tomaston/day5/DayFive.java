package dev.tomaston.day5;

import dev.tomaston.common.ChallengeConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayFive {

    private final String ROOT_PATH;

    Map<Integer, List<Integer>> map;
    List<List<Integer>> prerequisites;
    int ans = 0;
    int ans2 = 0;

    public DayFive(ChallengeConfig config) {
        this.ROOT_PATH = config.getRootPath();
        this.map = new HashMap<>();
        this.prerequisites = new ArrayList<>();
        parseInput();
    }

    public void firstChallenge() {
        System.out.println("D5C1 Answer: " + ans);
    }

    public void secondChallenge() {
        System.out.println("D5C2 Answer: " + ans2);
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
                    buildPrerequisites(line);
                } else if (line.length() > 1) {
                    if (isValid(line)) {
                        int middleNumber = getMiddle(line);
                        ans += middleNumber;
                    } else {
                        int[] numbers = getListNumbers(line);
                        List<Integer> sorted = getSortedOrder(numbers);
                        int mid = sorted.get(sorted.size() / 2);
                        ans2 += mid;
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

    private void buildPrerequisites(String rule) {
        String[] numbers = rule.split("\\|");
        assert(numbers.length == 2);

        //page X must occur before page Y
        int pageX = Integer.parseInt(numbers[0]);
        int pageY = Integer.parseInt(numbers[1]);

        prerequisites.add(new ArrayList<>(Arrays.asList(pageX, pageY)));
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


    private List<Integer> getSortedOrder(int[] numbers) {
        List<List<Integer>> prereqs = new ArrayList<>();
        for (List<Integer> prerequisite : prerequisites) {
            assert(prerequisite.size() == 2);
            if (numbersContains(numbers, prerequisite.getFirst(), prerequisite.getLast())) {
                prereqs.add(prerequisite);
            }
        }

        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int num : numbers) {
            inDegree.put(num, 0);
        }

        for (List<Integer> prereq : prereqs) {
            inDegree.put(prereq.getLast(), inDegree.get(prereq.getLast()) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer curr = queue.remove();
            ans.add(curr);

            if (map.containsKey(curr)) {
                for (int neighbor : map.get(curr)) {
                    if (!inDegree.containsKey(neighbor)) continue;
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if(inDegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return ans;
    }

    private boolean numbersContains(int[] numbers, int xPage, int yPage) {
        boolean x = false;
        boolean y = false;
        for (int number : numbers) {
            if (number == xPage) x = true;
            if (number == yPage) y = true;
        }
        return x && y;
    }
}
