package com.javarush.task.task27.task2712.kitchen;

/**
 * Created by 1 on 11.11.2017.
 */
public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    /**
     * Getter in min.
     * @return
     */
    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
        StringBuilder builder = new StringBuilder(Dish.Fish.toString());
        for (int i = 1 ; i < Dish.values().length; i++) {
            builder.append(", " + Dish.values()[i]);
        }
        return builder.toString();
    }
}
