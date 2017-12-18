package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";
        ArrayList<FileInputStream> list = new ArrayList<>();

        while (true) {
            try {
                fileName = reader.readLine();
                list.add(new FileInputStream(fileName));
            } catch (FileNotFoundException exc) {
                System.out.println(fileName);
                break;
            }
        }

        reader.close();
        for (FileInputStream item : list) {
            item.close();
        }
    }
}
