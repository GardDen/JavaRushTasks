package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;


/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //ArrayList<String> list = new ArrayList<>();
        String text;
        //boolean flag = true;
        while (true){
            text = reader.readLine();
            if (text.equals("exit")) break;
            if (isDigitD(text)){
                if (isDigitN(text)){
                    int number = Integer.parseInt(text);
                    if (number > 0 && number < 128) {
                            print(Short.parseShort(text));
                    } else print(number);
                }
                else print(Double.parseDouble(text));
            }
            else print(text);
        }
    }

    private static boolean isDigitD(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDigitN(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
