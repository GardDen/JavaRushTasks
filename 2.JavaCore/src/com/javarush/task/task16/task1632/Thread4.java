package com.javarush.task.task16.task1632;

/**
 * Created by 1 on 07.04.2017.
 */
public class Thread4 extends Thread implements Message{
    @Override
    public void showWarning() {
        Thread4.interrupted();
    }
}
