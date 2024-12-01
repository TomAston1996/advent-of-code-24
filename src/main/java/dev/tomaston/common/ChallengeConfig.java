package dev.tomaston.common;

import java.util.Objects;

/**
 * App config
 */
public class ChallengeConfig {
    private final String rootPath;

    /**
     * App config setup
     */
    public ChallengeConfig() {
        this.rootPath = Objects.requireNonNull(Thread.currentThread()
                        .getContextClassLoader()
                        .getResource(""))
                        .getPath();

        System.out.println("Root path is: " + rootPath);
    }

    public String getRootPath() {
        return rootPath;
    }
}
