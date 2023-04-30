package ru.academits.kozhevnikov.minesweeper.logic;

import ru.academits.kozhevnikov.minesweeper.logic.high_scores_table.HighScoreTable;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinesweeperGame implements Game {
    private final static String HIGH_SCORES_DATA_PATH = "Minesweeper/src/ru/academits/kozhevnikov/minesweeper/resources/high_scores/highscores.txt";

    private final Difficulty difficulty;

    private final int width;
    private final int height;

    // Game state
    private final int[][] nearbyMinesCountMatrix; // -1 stands for mine
    private final int[][] revealedCellsMatrix;
    private int closedCellsCount;
    private boolean isWinner;
    private boolean isGameOver;

    private final MinesweeperTimeCounter timeCounter;
    private HighScoreTable highScoreTable;

    public MinesweeperGame(Difficulty difficulty) {
        this.difficulty = difficulty;

        height = difficulty.getMinefieldHeight();
        width = difficulty.getMinefieldWidth();

        nearbyMinesCountMatrix = new int[height][width];
        revealedCellsMatrix = new int[height][width];

        closedCellsCount = height * width;

        timeCounter = new MinesweeperTimeCounter();

        loadHighScoreTable();
    }

    public void revealCellRange(int revealedCellX, int revealedCellY) {
        if (isGameOver || isWinner) {
            return;
        }

        // The first cell is revealed, game starts
        if (closedCellsCount == height * width) {
            timeCounter.start();
            initializeGame(revealedCellX, revealedCellY);
        }

        closedCellsCount -= 1;
        revealedCellsMatrix[revealedCellY][revealedCellX] = 1;
        int nearbyMinesCountRevealed = nearbyMinesCountMatrix[revealedCellY][revealedCellX];

        // Open a single cell
        if (nearbyMinesCountRevealed != 0) {
            // Mine is revealed -> Game is over, timer stopped
            if (nearbyMinesCountRevealed == -1) {
                isGameOver = true;
                timeCounter.stop();
                return;
            }
        }

        // If revealed cell is empty, open adjacent empty cells range
        Deque<Integer> cellsIndicesQueue = new LinkedList<>();
        cellsIndicesQueue.addLast(revealedCellX + width * revealedCellY);

        while (!cellsIndicesQueue.isEmpty()) {
            int unifiedIndex = cellsIndicesQueue.removeFirst();

            int currentCellY = unifiedIndex / width;
            int currentCellX = unifiedIndex % width;

            // Revealing neighbouring cells
            if (nearbyMinesCountMatrix[currentCellY][currentCellX] == 0) {
                for (int j = currentCellY - 1; j < currentCellY + 2; ++j) {
                    for (int i = currentCellX - 1; i < currentCellX + 2; ++i) {
                        if (isInRange(i, j) && revealedCellsMatrix[j][i] == 0) {
                            revealedCellsMatrix[j][i] = 1;
                            closedCellsCount -= 1;
                            cellsIndicesQueue.addLast(i + j * width);
                        }
                    }
                }
            }
        }

        if (closedCellsCount == difficulty.getMinesCount()) {
            timeCounter.stop();
            isWinner = true;
        }
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public HighScoreTable getHighScoresTable() {
        return highScoreTable;
    }

    public int getClosedCellsCount() {
        return closedCellsCount;
    }

    public void addNewHighScore(Difficulty difficulty, String nickname, long gameTime) {
        highScoreTable.addHighScoreRecord(difficulty, nickname, gameTime);
        saveHighScoreTable();
    }

    public long getGameTime() {
        return timeCounter.getGameTime();
    }

    public int[][] getRevealedCellsMatrix() {
        return revealedCellsMatrix;
    }

    public int[][] getNearbyMinesCountMatrix() {
        return nearbyMinesCountMatrix;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public boolean isNewHighScore() {
        return highScoreTable.isValidToAdd(getGameTime());
    }

    private void initializeGame(int firstRevealedCellX, int firstRevealedCellY) {
        int minesCount = difficulty.getMinesCount();
        int excludedIndex = firstRevealedCellX + firstRevealedCellY * width;

        // Get cells indices to place mines except first revealed cell
        Integer[] randomIndices = getRandomCellsIndices(minesCount, excludedIndex);

        // Place mines
        for (int k = 0; k < minesCount; ++k) {
            int cellY = randomIndices[k] / width;
            int cellX = randomIndices[k] % width;

            nearbyMinesCountMatrix[cellY][cellX] = -1;

            for (int j = cellX - 1; j < cellX + 2; ++j) {
                for (int i = cellY - 1; i < cellY + 2; ++i) {
                    if (isInRange(j, i) && nearbyMinesCountMatrix[i][j] != -1) {
                        nearbyMinesCountMatrix[i][j] += 1;
                    }
                }
            }
        }
    }

    private Integer[] getRandomCellsIndices(int numbersCount, int excludedNumber) {
        List<Integer> cellsIndices = IntStream.range(0, height * width - 2)
                .filter(index -> index != excludedNumber)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(cellsIndices);

        Integer[] randomCellIndices = new Integer[numbersCount];
        new ArrayList<>(cellsIndices.subList(0, numbersCount)).toArray(randomCellIndices);

        return randomCellIndices;
    }

    private void loadHighScoreTable() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(HIGH_SCORES_DATA_PATH))) {
            highScoreTable = (HighScoreTable) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            highScoreTable = new HighScoreTable();
        }
    }

    private void saveHighScoreTable() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(HIGH_SCORES_DATA_PATH))) {
            outputStream.writeObject(highScoreTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isInRange(int column, int row) {
        return column >= 0 && row >= 0 && column < width && row < height;
    }
}
