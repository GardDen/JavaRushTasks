package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine(), fileName2 = reader.readLine();
        reader.close();

        /*String fileName1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt";
        String fileName2 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\2.txt";*/


        FileReader fileReader = new FileReader(fileName1);//поток чтения из файлв
        BufferedReader bufferedReader = new BufferedReader(fileReader);//буфер, хз как он работает

        FileWriter fileWriter = new FileWriter(fileName2);// поток записи в файл
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);// буфер для записи

        String temp = "";//переменная в которую записываем весь текст из первого файла используем её вместо буфера
        while (fileReader.ready()) {//метод реади проверяет все ли считали из файла если есть что читать то возращает истину
            temp += ((char) fileReader.read());//чар в с кобках это приведение типа инт к чару, потому что метод реад возращает считанные байты в типе инт
        }

        String[] bufferWords = temp.split(" ");

        for (int i = 0; i < bufferWords.length; i++) {
            if (isDigit(bufferWords[i])){
                fileWriter.write(bufferWords[i] + " ");
            }
        }

        bufferedReader.close();//непонятная фигня которая закрывает потоки и освобождает ресурсы компа
        bufferedWriter.close();

    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
