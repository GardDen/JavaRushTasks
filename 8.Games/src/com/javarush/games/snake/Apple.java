package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class Apple extends GameObject {
    private static final String APPLE_SIGN = "\u058D";
    public boolean isAlive = true;

    /**
     * В методе draw(Game) должен быть вызывать метод setCellValueEx(int, int, Color, String, Color, int)
     * у объекта типа Game с параметрами: x, y, Color.NONE, APPLE_SIGN, <цвет яблока>, 75.
     * (<цвет яблока> используй какой тебе нравится, например, Color.GREEN).
     * @param game
     */
    public void draw(Game game) {
        game.setCellValueEx(this.x, this.y, Color.NONE, APPLE_SIGN, Color.RED, 75);
    }

    public Apple(int x, int y) {
        super(x, y);
    }
}
