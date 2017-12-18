package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        read();
    }


    public static void read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine(), fileName2 = reader.readLine();
        /*String fileName1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt";
        String fileName2 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\2.txt";*/

        FileInputStream fileInputStream = new FileInputStream(fileName1);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName2);
        byte[] buffer = new byte[fileInputStream.available()];

        fileInputStream.read(buffer, 0, fileInputStream.available());

        for (int i = buffer.length - 1; i >= 0; i--) {
            fileOutputStream.write(buffer[i]);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
