package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        String filrName1 = args[0];//"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1921\\test.txt";
        String fileName2 = args[1];//"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1921\\result.txt";
        ArrayList<String> listStr = readFile(filrName1);
        ArrayList<String> separatingListStr = new ArrayList<>();
        for (String str : listStr) {
            String[] bufWords = str.split(" ");
            for (String word : bufWords) {
                if (word.length() > 6) {
                    separatingListStr.add(word);
                }
            }
        }

        FileWriter fileWriter = new FileWriter(fileName2);
        for (int i = 0; i < separatingListStr.size() - 1; i++) {
            fileWriter.write(separatingListStr.get(i) + ",");
        }
        fileWriter.write(separatingListStr.get(separatingListStr.size() - 1));
        fileWriter.close();
    }

    /**
     * Метод считывает файл построчно и возвращаетв список строк
     * @param nameFile имя файла, который читать
     * @return список строк считанных из файла
     */

    public static ArrayList<String> readFile (String nameFile) {
        ArrayList<String> buffer = new ArrayList<>();
        String data;
        try{
            BufferedReader br = new BufferedReader(new FileReader(nameFile));
            while((data = br.readLine())!= null) {
                buffer.add(data);
            }
            br.close();
        }
        catch (IOException exception){
            System.out.println("Ошибка чтения из файла: " + exception.toString());
        }
        return buffer;
    }
}
