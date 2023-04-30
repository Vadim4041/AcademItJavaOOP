package ru.academits.kozhevnikov.minesweeper.logic.high_scores_table;

import ru.academits.kozhevnikov.minesweeper.logic.Difficulty;

import java.io.Serializable;

public class HighScoreRecord implements Serializable {
    private final String difficulty;
    private final String nickName;
    private final long gameTime;

    public HighScoreRecord(Difficulty difficulty, String nickName, long gameTime) {
        this.difficulty = difficulty.toString();
        this.nickName = nickName;
        this.gameTime = gameTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public long getGameTime() {
        return gameTime;
    }

    public String getNickName() {
        return nickName;
    }

    public String toString() {
        return String.format("%s: %s - %.1f sec", difficulty, nickName, (double) gameTime / 1000);
    }
}