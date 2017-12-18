package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread thread;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted())
        {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    @Override
    public void start(String threadName) {
        TaskManipulator taskManipulator = new TaskManipulator();
        thread = new Thread(taskManipulator);
        thread.setName(threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
