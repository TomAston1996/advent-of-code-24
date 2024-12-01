package dev.tomaston;

import dev.tomaston.common.ChallengeConfig;
import dev.tomaston.day1.DayOneC1;

/**
 *   ___ ______ _   _ _____ _   _ _____   ___________     _____ ___________ _____
 *  / _ \|  _  \ | | |  ___| \ | |_   _| |  _  |  ___|  /  __ \  _  |  _  \  ___|
 * / /_\ \ | | | | | | |__ |  \| | | |   | | | | |_    | /  \/ | | | | | | |__
 * |  _  | | | | | | |  __|| . ` | | |   | | | |  _|   | |   | | | | | | |  __|
 * | | | | |/ /\ \_/ / |___| |\  | | |   \ \_/ / |     | \__/\ \_/ / |/ /| |___
 * \_| |_/___/  \___/\____/\_| \_/ \_/    \___/\_|      \____/\___/|___/ \____/
 *
 * App entry class for running daily challenges
 */
public class Main {
    public static void main(String[] args) {
        ChallengeConfig config = new ChallengeConfig();
        DayOneC1 dayOneC1 = new DayOneC1(config);
        dayOneC1.FirstChallenge();

    }
}