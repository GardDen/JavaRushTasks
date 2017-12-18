package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt";
        String file2 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\2.txt";
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine(), file2 = reader.readLine();
        reader.close();*/

        FileInputStream fileInputStream = new FileInputStream(file1);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        String temp = "";

        while(fileInputStream.available() > 0) {
            temp += (char)fileInputStream.read();
        }
        String[] massiv = temp.trim().split("\\s+");
        int i = 0;
        while (i < massiv.length) {
            int N = (int)Math.round(Double.parseDouble(massiv[i]));
            fileOutputStream.write(N);
            System.out.println(N);
            fileOutputStream.write(" ".getBytes());
            i++;
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
