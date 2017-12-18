package com.javarush.task.task25.task2506;

import java.beans.Statement;

/**
 * Created by 1 on 16.10.2017.
 */
public class LoggingStateThread extends Thread {
    private Thread thread;
    private State prevState = null;
    private State state;
    private boolean flagRunable = false;
    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }
    @Override
    public void run() {
        while (true) {
            state = thread.getState();
            if (prevState == null) {
                prevState = state;
                System.out.println(state);
            } else if (prevState != state) {
                System.out.println(state);
                prevState = state;
            }
            if (state == State.TERMINATED) {break; }
        }
    }
}
