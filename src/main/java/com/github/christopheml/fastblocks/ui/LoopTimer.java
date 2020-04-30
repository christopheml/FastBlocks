package com.github.christopheml.fastblocks.ui;

/**
 * Allows code execution at a specified interval during the game loop.
 */
public class LoopTimer {

    private long intervalMs;

    private long lastRun;

    public LoopTimer(long intervalMs) {
        this.intervalMs = intervalMs;
        lastRun = 0;
    }

    public void runOnInterval(long now, Runnable action) {
        if (elapsedMs(now) > intervalMs) {
            action.run();
            lastRun = now;
        }
    }

    public void setIntervalMs(long intervalMs) {
        this.intervalMs = intervalMs;
    }

    private long elapsedMs(long now) {
        return (now - lastRun) / 1000000;
    }

}
