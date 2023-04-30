package ru.academits.kozhevnikov.minesweeper.main;

import ru.academits.kozhevnikov.minesweeper.controller.Controller;
import ru.academits.kozhevnikov.minesweeper.gui.MinesweeperView;
import ru.academits.kozhevnikov.minesweeper.gui.View;
import ru.academits.kozhevnikov.minesweeper.logic.Game;
import ru.academits.kozhevnikov.minesweeper.logic.Difficulty;
import ru.academits.kozhevnikov.minesweeper.logic.MinesweeperGame;

public class Main {
    public static void main(String[] args) {
        Game minesweeper = new MinesweeperGame(Difficulty.EASY);
        View view = new MinesweeperView(minesweeper);
        new Controller(minesweeper, view);
    }
}
