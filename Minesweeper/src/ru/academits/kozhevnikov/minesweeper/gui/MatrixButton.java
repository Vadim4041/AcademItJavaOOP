package ru.academits.kozhevnikov.minesweeper.gui;

import javax.swing.JButton;
import java.awt.event.MouseListener;

public class MatrixButton {
    private final JButton button;

    private final int buttonY;
    private final int buttonX;
    private boolean isFlagged;

    public MatrixButton(int buttonX, int buttonY) {
        button = new JButton();
        this.buttonY = buttonY;
        this.buttonX = buttonX;
    }

    public int getButtonY() {
        return buttonY;
    }

    public int getButtonX() {
        return buttonX;
    }

    public void setFlagged() {
        isFlagged = true;
    }

    public void setUnFlagged() {
        isFlagged = false;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void addMouseListener(MouseListener mouseListener) {
        button.addMouseListener(mouseListener);
    }

    public JButton getButton() {
        return button;
    }
}
