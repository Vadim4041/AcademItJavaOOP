package ru.academits.kozhevnikov.minesweeper.logic.high_scores_table;

import java.util.Comparator;

public class HighScoreRecordsComparator implements Comparator<HighScoreRecord> {
    @Override
    public int compare(HighScoreRecord record1, HighScoreRecord record2) {
        return Long.compare(record1.getGameTime(), record2.getGameTime());
    }
}
