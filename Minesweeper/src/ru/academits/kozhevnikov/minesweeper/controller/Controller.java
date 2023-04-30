package ru.academits.kozhevnikov.minesweeper.controller;

import ru.academits.kozhevnikov.minesweeper.gui.View;
import ru.academits.kozhevnikov.minesweeper.logic.Game;
import ru.academits.kozhevnikov.minesweeper.logic.Difficulty;
import ru.academits.kozhevnikov.minesweeper.logic.MinesweeperGame;
import ru.academits.kozhevnikov.minesweeper.logic.high_scores_table.HighScoreRecord;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Controller extends MouseAdapter {
    private Game minesweeper;
    private final View view;

    public Controller(Game minesweeper, View view) {
        this.minesweeper = minesweeper;
        this.view = view;
        this.view.setHomeButton(this, new StartNewGamePopupMenu(), new HighScoresTablePresenter(), new InformationAboutThisGamePresenter());
        new GameTimeListener();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // Do not restart game if current is not started yet
            if (minesweeper.getClosedCellsCount() == minesweeper.getDifficulty().getMinefieldHeight() * minesweeper.getDifficulty().getMinefieldWidth()) {
                return;
            }

            minesweeper = new MinesweeperGame(minesweeper.getDifficulty());
            view.initializeGui(minesweeper);
            view.setGameTime(0L);
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            view.showPopupMenu(e);
        }
    }

    class StartNewGamePopupMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            minesweeper = new MinesweeperGame(Difficulty.valueOf(e.getActionCommand()));
            view.initializeGui(minesweeper);
            view.setGameTime(0L);
        }
    }

    class HighScoresTablePresenter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] headers = {"#", "NAME", "DIFFICULTY", "BEST TIME, SEC"};

            LinkedList<HighScoreRecord> records = minesweeper.getHighScoresTable().getHighScoreRecords();
            int recordsCount = records.size();
            String[][] rows = new String[recordsCount][4];

            int i = 0;

            for (HighScoreRecord record : records) {
                rows[i][0] = Integer.toString(i + 1);
                rows[i][1] = record.getNickName();
                rows[i][2] = record.getDifficulty();
                rows[i][3] = String.format("%.3f", (double) record.getGameTime() / 1000);
                ++i;
            }

            JTable highScoreTable = new JTable(rows, headers);
            highScoreTable.setEnabled(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(highScoreTable), "HIGH SCORES", JOptionPane.PLAIN_MESSAGE);
        }
    }

    class GameTimeListener implements ActionListener {
        Timer timer;

        public GameTimeListener() {
            timer = new Timer(100, this);
            timer.setInitialDelay(0);
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setGameTime(minesweeper.getGameTime());
        }
    }

    static class InformationAboutThisGamePresenter implements ActionListener {
        private final static String BOMB_IMAGE_PATH = "Minesweeper/src/ru/academits/kozhevnikov/minesweeper/resources/images/bomb.png";
        private final static int CELL_ICON_SIZE = 40;

        @Override
        public void actionPerformed(ActionEvent e) {
            ImageIcon bombIcon = new ImageIcon(
                    new ImageIcon(BOMB_IMAGE_PATH)
                            .getImage()
                            .getScaledInstance(CELL_ICON_SIZE, CELL_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
            );

            JOptionPane.showMessageDialog(null, "Minesweeper v1.0 (by Vadim Kozhevnikov)", "About", JOptionPane.INFORMATION_MESSAGE, bombIcon);
        }
    }
}
