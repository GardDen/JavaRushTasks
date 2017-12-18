package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int[] home = new int[15];
        int countEven = 0, countOdd = 0;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < home.length; i++){
            home[i] = scanner.nextInt();
            if (i % 2 == 0){
                countEven += home[i];
            }
            else {
                countOdd += home[i];
            }
        }
        if (countEven > countOdd){
            System.out.println("В домах с четными номерами проживает больше жителей.");
        }
        else {
            System.out.println("В домах с нечетными номерами проживает больше жителей.");
        }
    }
}
