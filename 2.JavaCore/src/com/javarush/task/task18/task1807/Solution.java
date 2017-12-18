package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();//"C:\\data.txt"

        //создаем поток считывания
        FileInputStream fileInputStream = new FileInputStream(fileName);
        int bytesAvailable = fileInputStream.available();//количество байтов в файле
        byte[] data = new byte[bytesAvailable];//создаем массив для хранени данных подходящего размера
        fileInputStream.read(data, 0, bytesAvailable);//читаем данные
        //print(data);
        parsing(data);
        fileInputStream.close();
    }

    public static void parsing(byte[] data) {
        //копируем данные из массива в список для удобства работы
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
        System.out.println(Collections.frequency(list,(byte)44));
    }

   /* public static void print(byte[] data) {
        System.out.println("Char:");
        for (int i = 0; i < data.length; i++) {
            System.out.println((char)data[i]);
        }
        System.out.println("Bytes:");
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }*/
}
