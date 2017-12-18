package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static TreeMap<String, byte[]> map = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;//D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1825\\porno.avi.part
        while (true) {
            fileName = reader.readLine();
            if (fileName.equals("end")) {
                break;
            } else {
                read(fileName);
            }
        }
        reader.close();
        fileName = map.firstKey();
        int index = fileName.lastIndexOf(".");
        fileName = fileName.substring(0, index );

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        for (Map.Entry<String, byte[]> item : map.entrySet()) {
            fileOutputStream.write(item.getValue());
        }
        fileOutputStream.close();
    }

    public static void read(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer);
        fileInputStream.close();
        map.put(fileName, buffer);
    }
}
