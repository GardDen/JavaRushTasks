package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels = new ArrayList<>();

        public Car() {
            //init wheels here
            try {
                if (loadWheelNamesFromDB().length != 4) {
                    throw new IllegalArgumentException();
                }
                for (String text: loadWheelNamesFromDB()) {


                    Wheel wheel = Wheel.valueOf(text);
                    wheels.add(wheel);
                }
            }
            catch (IllegalArgumentException exc) {
                throw new IllegalArgumentException("Машину на СТО не повезем!");
            }
        }
        
        

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
    }
}
