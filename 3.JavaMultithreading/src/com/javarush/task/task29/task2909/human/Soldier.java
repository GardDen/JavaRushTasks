package com.javarush.task.task29.task2909.human;

/**
 * Created by 1 on 24.10.2017.
 */
public class Soldier extends Human {
    public Soldier(String name, int age) {
        super(name, age);
    }

    public void live() {
            fight();
    }

    public void fight() {
    }

}
