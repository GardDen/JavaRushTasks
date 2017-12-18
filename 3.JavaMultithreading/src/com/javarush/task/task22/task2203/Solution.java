package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        String massiv[] = (string + " ").split("\\t");
        System.out.println(massiv.length);
        if (massiv.length < 3) {
            throw new TooShortStringException();
        }
        return massiv[1];
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
        //System.out.println(getPartOfString("dfg\tfdgfd"));
    }
}
