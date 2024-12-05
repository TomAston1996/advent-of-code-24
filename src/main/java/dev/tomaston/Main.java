package dev.tomaston;

import dev.tomaston.common.ChallengeConfig;
import dev.tomaston.day1.DayOne;
import dev.tomaston.day2.DayTwo;
import dev.tomaston.day3.DayThree;
import dev.tomaston.day4.DayFour;
import dev.tomaston.day5.DayFive;

/**
 *   ___ ______ _   _ _____ _   _ _____   ___________     _____ ___________ _____
 *  / _ \|  _  \ | | |  ___| \ | |_   _| |  _  |  ___|  /  __ \  _  |  _  \  ___|
 * / /_\ \ | | | | | | |__ |  \| | | |   | | | | |_    | /  \/ | | | | | | |__
 * |  _  | | | | | | |  __|| . ` | | |   | | | |  _|   | |   | | | | | | |  __|
 * | | | | |/ /\ \_/ / |___| |\  | | |   \ \_/ / |     | \__/\ \_/ / |/ /| |___
 * \_| |_/___/  \___/\____/\_| \_/ \_/    \___/\_|      \____/\___/|___/ \____/
 * App entry class for running daily challenges
 */
public class Main {
    public static void main(String[] args) {
        ChallengeConfig config = new ChallengeConfig();
        DayFive dayFive = new DayFive(config);
        dayFive.firstChallenge();
        dayFive.secondChallenge();
    }
}