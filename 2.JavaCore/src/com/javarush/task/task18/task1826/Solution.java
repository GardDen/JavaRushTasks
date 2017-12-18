package com.javarush.task.task18.task1826;

/* 
Шифровка
*/


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[1];//"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1826\\test1.txt";
        String fileOutputName = args [2];//"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1826\\test.txt";;
        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(fileOutputName);
        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer);
        for (int i = 0; i < buffer.length; i++) {
            fileOutputStream.write(128 - buffer[i]);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
