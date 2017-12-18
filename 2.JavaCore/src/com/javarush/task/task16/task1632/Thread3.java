package com.javarush.task.task16.task1632;

/**
 * Created by 1 on 07.04.2017.
 */
public class Thread3 extends Thread {
    public void run() {
        try
        {
            while (true) {
                System.out.println("Ура");
                Thread.sleep(500);
            }
        }
        catch (InterruptedException e)
        {
        }
    }
}
