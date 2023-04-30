package ru.academits.kozhevnikov.minesweeper.logic;

import ru.academits.kozhevnikov.minesweeper.logic.high_scores_table.HighScoreTable;

public interface Game {
    void revealCellRange(int cellX, int cellY);

    Difficulty getDifficulty();

    int getClosedCellsCount();

    HighScoreTable getHighScoresTable();

    boolean isGameOver();

    boolean isWinner();

    boolean isNewHighScore();

    void addNewHighScore(Difficulty difficulty, String nickname, long gameTime);

    int[][] getRevealedCellsMatrix();

    int[][] getNearbyMinesCountMatrix();

    long getGameTime();
}
