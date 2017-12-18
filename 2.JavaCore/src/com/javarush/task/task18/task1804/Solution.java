package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
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

        //при помощи метода Collections.frequency ищем количество повторов и добавляем в карту
        //после занесения в карту, удаляем все вхождения текучего байта в листе
        Map<Byte, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); ) {
            map.put(list.get(i), Collections.frequency(list, list.get(i)));
            byte test = list.get(i);
            //Очень важно удалять из конца списка, если удалять с начала, то список сдвигается, это создает проблемы
            for (int k = list.size() - 1; k >= 0; k--) {
                if (test == list.get(k)) {
                    list.remove(k);
                }
            }
        }

        /*
        Необходимо найти в карте байт с минимальным значение(количеством вхождений)
        Поэтому все значения запихиваем в лист и используем метод sort =)
         */
        ArrayList<Integer> listCount = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            listCount.add((Integer) entry.getValue());
        }
        Collections.sort(listCount);

        //Проходим по всей карте, и если значение карты соответствует минимальному то выводим в консоль ключ
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == listCount.get(0)) {
                System.out.print(entry.getKey() + " ");
            }
        }
    }

    /*public static void print(byte[] data) {
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
