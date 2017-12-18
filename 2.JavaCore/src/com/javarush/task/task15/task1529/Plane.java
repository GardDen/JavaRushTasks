package com.javarush.task.task15.task1529;

/**
 * Created by 1 on 31.03.2017.
 */
public class Plane implements Flyable {
    int countPassengers;

    public Plane(int countPassengers) {
        this.countPassengers = countPassengers;
    }

    @Override
    public void fly() {

    }
}
