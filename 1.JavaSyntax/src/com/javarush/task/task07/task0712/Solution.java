package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        list.add(reader.readLine());
        String maxText = list.get(0), minText = list.get(0);
        int iMax = 0, iMin = 0;
        for (int i = 1; i < 10; i++){
            list.add(reader.readLine());
            if (list.get(i).length() > maxText.length()) {
                maxText = list.get(i);
                iMax = i;
            }
            if (list.get(i).length() < minText.length()){
                minText = list.get(i);
                iMin = i;
            }
        }
        if (iMin < iMax) System.out.println(list.get(iMin));
        else System.out.println(list.get(iMax));
    }
}
