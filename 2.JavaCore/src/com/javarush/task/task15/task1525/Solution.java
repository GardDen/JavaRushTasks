package com.javarush.task.task15.task1525;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();
    static {
        String text;
        try {
            //Create stream and buffer, read dara from is fileName
            FileReader fileReader = new FileReader(Statics.FILE_NAME);
            BufferedReader br = new BufferedReader(fileReader);
            while ((text = br.readLine()) != null) {
                lines.add(text);
            }
            br.close();
            fileReader.close();
        } catch (IOException exc) {
            System.out.println("Ошибка чтене из файла: " + exc.toString());
        }
    }

    /**.*/
    public static void main(String[] args) throws IOException {
        System.out.println(lines);
    }
}
