package com.javarush.task.task19.task1906;

/* 
Четные байты
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine(), file2 = reader.readLine();
        reader.close();

        /*String file1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt";
        String file2 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\2.txt";*/


        FileReader fileReader = new FileReader(file1);
        FileWriter fileWriter = new FileWriter(file2);

        ArrayList<Character> list = new ArrayList<>();
        char temp;
        int count = 0;
        while (fileReader.ready()) {
            count++;
            temp = (char) fileReader.read();
            if (count % 2 == 0) {
                fileWriter.write(temp);
            }
        }

        fileReader.close();
        fileWriter.close();

    }
}
