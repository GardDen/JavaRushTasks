package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();

        for (int i = 0; i < list.size(); i++) {
            StringBuffer sbf = new StringBuffer(list.get(i));
            System.out.println(sbf.reverse());
        }

    }
}
