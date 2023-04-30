package ru.academits.kozhevnikov.minesweeper.logic;

public enum Difficulty {
    EASY(9, 9, 10),
    NORMAL(14, 14, 40),
    HARD(20, 20, 99);

    private final int minefieldWidth;
    private final int minefieldHeight;
    private final int minesCount;

    Difficulty(int minefieldWidth, int minefieldHeight, int minesCount) {
        this.minefieldHeight = minefieldHeight;
        this.minefieldWidth = minefieldWidth;
        this.minesCount = minesCount;
    }

    public int getMinefieldWidth() {
        return minefieldWidth;
    }

    public int getMinefieldHeight() {
        return minefieldHeight;
    }

    public int getMinesCount() {
        return minesCount;
    }
}
