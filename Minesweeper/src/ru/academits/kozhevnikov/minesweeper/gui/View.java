package ru.academits.kozhevnikov.minesweeper.gui;

import ru.academits.kozhevnikov.minesweeper.logic.Game;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public interface View {
    void setHomeButton(MouseAdapter resetGameMouseAdapter, ActionListener startNewGamePopupMenuListener, ActionListener highScoresListener, ActionListener aboutThisGameListener);

    void showPopupMenu(MouseEvent e);

    void initializeGui(Game minesweeper);

    void setGameTime(long gameTime);
}
