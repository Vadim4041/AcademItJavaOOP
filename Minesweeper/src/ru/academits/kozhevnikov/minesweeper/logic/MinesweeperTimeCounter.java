package ru.academits.kozhevnikov.minesweeper.logic;

import java.util.Date;

public class MinesweeperTimeCounter {
    private long startTime;
    private long endTime;
    private boolean isStopped;

    public MinesweeperTimeCounter() {
        isStopped = true;
    }

    public void start() {
        isStopped = false;
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        isStopped = true;
        this.endTime = new Date().getTime();
    }

    public long getGameTime() {
        return (isStopped ? endTime : new Date().getTime()) - startTime;
    }
}
