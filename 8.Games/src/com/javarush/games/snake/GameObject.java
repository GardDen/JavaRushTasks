package com.javarush.games.snake;

public class GameObject {
    public int x;
    public int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
