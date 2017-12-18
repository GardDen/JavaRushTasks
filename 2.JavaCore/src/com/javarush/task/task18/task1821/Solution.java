package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    public static ArrayList <Integer> list = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        String fileName = args[0];//"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1821\\test.txt";

        FileReader fileReader = new FileReader(fileName);
        ArrayList<Massiv2D> ascii = new ArrayList<>();

        while (fileReader.ready()) {
            list.add(fileReader.read());
        }

        fileReader.close();

        while (true) {
            try {
                ascii.add(new Massiv2D(list.get(0), analyz(list.get(0))));
            } catch (IndexOutOfBoundsException exc) {
                break;
            }
        }

        Collections.sort(ascii, new Comparator<Massiv2D>() {
            @Override
            public int compare(Massiv2D o1, Massiv2D o2) {
                return o1.getNumberChar() - o2.getNumberChar();
            }
        });

        for (int i = 0; i < ascii.size(); i++) {
           System.out.println((char)ascii.get(i).getNumberChar() + " " + ascii.get(i).getCountChar());
        }
    }

    public static int analyz(int numberCharAscii) {
        int count = 0;
        for (int i = 0; i < list.size();) {
            if (list.get(i) == numberCharAscii) {
                count++;
                list.remove(i);
            } else i++;
        }
        return count;
    }

    public static class Massiv2D {
        private int numberChar;
        private int countChar;

        public Massiv2D(int numberChar, int countChar) {
            this.numberChar = numberChar;
            this.countChar = countChar;
        }

        public int getNumberChar() {
            return numberChar;
        }

        public int getCountChar() {
            return countChar;
        }
    }
}
