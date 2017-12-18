package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        //fileName = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task13\\task1326\\Test.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner inputStream = new Scanner(new FileInputStream(reader.readLine()));
        ArrayList<Integer> list = new ArrayList<>();
        String text = "";
        while (inputStream.hasNextInt()){
            int data = inputStream.nextInt();
            char simbol = (char)data;
                if (data % 2 == 0) {
                    list.add(data);
                }
        }
        inputStream.close();
        reader.close();
        Collections.sort(list);
        for (int i : list) {
            System.out.println(i);
        }
    }
}
