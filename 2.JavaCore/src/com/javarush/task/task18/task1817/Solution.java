package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader stream = new FileReader(args[0] /*"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt"*/);
        int countSpace = 0, countSimbols = 0;
        char temp;

        while (stream.ready()) {
            countSimbols++;
            temp = (char)stream.read();
            if (temp == 32){
                countSpace++;
            }
        }
        System.out.printf("%.2f", (double) countSpace/countSimbols * 100);
        stream.close();
    }
}
