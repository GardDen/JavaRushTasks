package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 10;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private final static String MINE = "\uD83D\uDCA3";
    private final static String FLAG = "\uD83D\uDEA9";
    private int countFlags;
    private boolean isGameStopped;
    private int countClosedTiles = SIDE * SIDE;
    private int score;

    @Override
    public void onMouseRightClick(int x, int y) {
        super.onMouseRightClick(x, y);
        markTile(x, y);
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        super.onMouseLeftClick(x, y);
        if (isGameStopped) {
            restart();
            return;
        }
        openTile(x, y);
    }

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean flag = false;
                if (getRandomNumber(10) == 0) {
                    flag = true;
                    countMinesOnField++;
                }
                gameField[x][y] = new GameObject(y, x, flag);
                setCellColor(x, y, Color.WHITE);
                setCellValue(x, y, "");
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private void markTile(int x, int y) {
        if (gameField[x][y].isOpen || isGameStopped==true) {
            return;
        }
        if (countFlags == 0 && !gameField[x][y].isFlag) {
            return;
        }

        if (!gameField[x][y].isFlag) {
            gameField[x][y].isFlag = true;
            countFlags--;
            setCellValueEx(x, y, Color.GOLD, FLAG);
        } else {
            gameField[x][y].isFlag = false;
            countFlags++;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.WHITE);
        }
    }

    private void openTile(int x, int y) {
        if (!gameField[x][y].isOpen && !gameField[x][y].isFlag && !isGameStopped) {
            gameField[x][y].isOpen = true;
            countClosedTiles--;
            setCellColor(x, y, Color.GREEN);
            if (gameField[x][y].isMine) {
                setCellValueEx(x, y, Color.RED, MINE);
                gameOver();
            } else {
                score+=5;
                setScore(score);
                if (gameField[x][y].countMineNeighbors == 0) {
                    for (GameObject iter : getNeighbors(gameField[x][y])) {
                        if (!iter.isOpen) {
                            openTile(iter.y, iter.x);
                        }
                    }
                    setCellValue(x, y, "");
                } else {
                    setCellNumber(x, y, gameField[x][y].countMineNeighbors);
                }
                if (countClosedTiles == countMinesOnField) {
                    win();
                }
            }
        }

    }

    /**
     * Возвращает для объекта типа GameObject список всех существующих соседей по восьми направлениям.
     * @param object
     * @return
     */
    private List<GameObject> getNeighbors(GameObject object) {
        List<GameObject> listNeighbors = new ArrayList<>();
        for (int dx = -1; dx < 2; dx++)
            for (int dy = -1; dy < 2; dy++) {
                int nX = object.x + dx;
                int nY = object.y + dy;
                if (!(nX < 0 || nY < 0 || nX > SIDE - 1 || nY > SIDE - 1 || (nX == object.x && nY == object.y))) {
                    listNeighbors.add(gameField[nY][nX]);
                }
            }
        return listNeighbors;
    }

    /**
     * Lля каждого элемента "не мины" матрицы gameField
     * считает количество соседних ячеек-мин и устанавливает это значение в поле countMineNeighbors
     */
    private void countMineNeighbors() {
        for(int y = 0; y < SIDE; y++){
            for(int x = 0; x < SIDE; x++){
                if(!gameField[x][y].isMine){
                    List<GameObject> list = getNeighbors(gameField[x][y]);
                    for (GameObject iter : list) {
                        if (iter.isMine) {
                            gameField[x][y].countMineNeighbors++;
                        }
                    }
                }
            }
        }
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.GOLD, "VICTORY", Color.BLACK, 80);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "GAME OVER", Color.RED, 80);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        countFlags = 0;
        countMinesOnField = 0;
        //score = 0;
        //setScore(score);
        createGame();

    }
}
