package com.javarush.task.task16.task1632;

/**
 * Created by 1 on 07.04.2017.
 */
public class Thread2 extends Thread {
    public void run() {
        try {
            while (!Thread2.currentThread().isInterrupted()) { }
            throw new InterruptedException();
        } catch (InterruptedException exc) {
            System.out.println("InterruptedException");
        }
    }
}
