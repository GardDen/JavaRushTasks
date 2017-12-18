package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        /*String file1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt";
        String file2 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\2.txt";*/
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine(), file2 = reader.readLine();
        FileInputStream fileInputStream = new FileInputStream(file1);
        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer, 0, fileInputStream.available());
        /* for(int i=0; i<buffer.length;i++){
            System.out.print((char)buffer[i]);
        }*/

        FileInputStream fileInputStream2 = new FileInputStream(file2);
        FileOutputStream fileOutputStream1 = new FileOutputStream(file1);
        FileOutputStream fileOutputStream2 = new FileOutputStream(file1, true);

        while (fileInputStream2.available() > 0) {
            fileOutputStream1.write(fileInputStream2.read());
        }
        for (int i = 0; i < buffer.length; i++) {
            fileOutputStream2.write(buffer[i]);
        }

        fileOutputStream1.close();
        fileOutputStream2.close();
        fileInputStream.close();
        fileInputStream2.close();
    }
}
