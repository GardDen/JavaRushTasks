package com.javarush.task.task16.task1603;

import java.util.ArrayList;
import java.util.List;

/* 
Список и нити
*/

public class Solution {
    public static volatile List<Thread> list = new ArrayList<Thread>(5);

    /**.
     *
     * @param args dfgdg/
     */
    public static void main(String[] args) {
        //Add your code here - добавьте свой код тут
        for (int i = 0; i < 5; i ++) {
            list.add(new Thread(new SpecialThread()));
        }
        //list.get(1).start();
    }

    public static class SpecialThread implements Runnable {
        public void run() {
            System.out.println("it's a run method inside SpecialThread");
        }
    }
}
