package com.javarush.task.task14.task1419;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //it's first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        //напишите тут ваш код
        try {
            int i = 1 / 0;

        } catch (ArithmeticException e) {
            exceptions.add(e);
        }
        //3
        try {
            int[] i = {0, 1};
            System.out.println(i[3]);
        } catch (IndexOutOfBoundsException e) {
            exceptions.add(e);
        }
        //4
        try {
            String s= "f";
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            exceptions.add(e);
        }
        //5
        try {
            Solution solution = null;
            solution.equals(solution);
        } catch (NullPointerException e) {
            exceptions.add(e);
        }
        //6
        try {
            Solution solution = new Solution();
            solution.clone();
        } catch (CloneNotSupportedException e) {
            exceptions.add(e);
        }
        //7
        try {
            throw new IllegalStateException();
        } catch (IllegalStateException e) {
            exceptions.add(e);
        }
        //8
        try {
            throw new IllegalMonitorStateException();
        } catch (IllegalMonitorStateException  e) {
            exceptions.add(e);
        }
        //9
        try {
            throw new IllegalThreadStateException();
        } catch (IllegalThreadStateException  e) {
            exceptions.add(e);
        }
        //10
        try {
            throw new SecurityException();
        } catch (SecurityException  e) {
            exceptions.add(e);
        }
    }
}
