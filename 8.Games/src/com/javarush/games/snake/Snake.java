package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake extends GameObject {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        super(x, y);
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));

    }

    public void draw(Game game) {
        Color color = Color.BLACK;
        if (!isAlive) {
            color = Color.RED;
        }
        game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, color, 75);
        for (int i = 1; i < snakeParts.size(); i++) {
            game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, color, 75);
        }

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        GameObject head = createNewHead();
        if (head.x < 0 || head.x >= SnakeGame.WIDTH || head.y < 0 || head.y >= SnakeGame.HEIGHT) {
            isAlive = false;
            return;
        }

        //добавление головы
        ArrayList<GameObject> dest = new ArrayList<>(snakeParts);
        snakeParts.clear();
        snakeParts.add(head);
        snakeParts.addAll(dest);

        removeTail();
    }

    public GameObject createNewHead() {
        int headX = snakeParts.get(0).x;
        int headY = snakeParts.get(0).y;
        switch (direction) {
            case LEFT:
                headX--;
                break;
            case RIGHT:
                headX++;
                break;
            case UP:
                headY--;
                break;
            case DOWN:
                headY++;
                break;
        }
        return new GameObject(headX, headY);
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }
}
