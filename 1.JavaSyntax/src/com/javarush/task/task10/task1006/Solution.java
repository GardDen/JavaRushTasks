package com.javarush.task.task10.task1006;

/* 
Задача №6 на преобразование целых типов
*/

public class Solution {
    public static void main(String[] args) {
        short b = (short) 45;
        char c = (short) 'c';//99
        short s = (short) 1005.22;//1005
        int i = 150000;//18928
        float f = 4.10f;//4
        double d = 1.256d;//1
        double result = (f * b) + (i / c) - (d * s) + 562.78d;
        //System.out.println(b + " " + (int)c + " " + s + " " + i +  " " + f + " " + d);
        //System.out.println((int)562.78d);
        System.out.println("result: " + result);
    }
}