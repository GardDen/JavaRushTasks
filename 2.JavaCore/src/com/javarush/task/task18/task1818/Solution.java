package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        /*String file1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt";
        String file2 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\2.txt";
        String file3 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\3.txt";*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine(), file2 = reader.readLine(), file3 = reader.readLine();

        FileInputStream fileInputStream1 = new FileInputStream(file2);
        FileInputStream fileInputStream2 = new FileInputStream(file3);
        FileOutputStream fileOutputStream = new FileOutputStream(file1, true);

        while (fileInputStream1.available() > 0) {
            fileOutputStream.write(fileInputStream1.read());
        }

        while (fileInputStream2.available() > 0) {
            fileOutputStream.write(fileInputStream2.read());
        }

        fileOutputStream.close();
        fileInputStream1.close();
        fileInputStream2.close();

    }
}
