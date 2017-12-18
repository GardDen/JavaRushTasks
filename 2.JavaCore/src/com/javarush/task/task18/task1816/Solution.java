package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        FileReader fileReader = new FileReader(fileName);
        int count = 0;
        char temp;

        while (fileReader.ready()) {
            temp = (char)fileReader.read();
            if (((temp >= 'a')&&(temp <= 'z')) || ((temp >= 'A')&&(temp <= 'Z'))){
                count++;
            }
        }
        fileReader.close();
        System.out.println(count);
    }
}
