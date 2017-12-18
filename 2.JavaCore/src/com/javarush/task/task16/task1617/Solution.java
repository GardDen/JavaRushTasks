package com.javarush.task.task16.task1617;

/* 
Отсчет на гонках
*/

public class Solution {
    public static volatile int countSeconds = 7;

    /**.*/
    public static void main(String[] args) throws InterruptedException {
        RacingClock clock = new RacingClock();
        //add your code here - добавь код тут

        Thread.sleep(3500);
        clock.interrupt();
    }

    public static class RacingClock extends Thread {
        public int count;

        /**.*/
        public RacingClock() {
            start();
        }

        /**.*/
        public void run() {
            //add your code here - добавь код тут
            try {
                while (countSeconds > 0) {
                    System.out.print(countSeconds-- + " ");
                    Thread.sleep(1000);
                }
                System.out.println("Марш!");
            } catch (InterruptedException exc) {
                    System.out.println("Прервано!");

            }
        }

    }
}
