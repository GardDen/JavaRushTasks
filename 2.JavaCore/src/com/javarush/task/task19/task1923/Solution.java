package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        String filrName1 = args[0];//"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1921\\test.txt";
        String fileName2 = args[1];//"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1923\\result.txt";
        ArrayList<String> listStr = readFile(filrName1);
        FileWriter fileWriter = new FileWriter(fileName2);
        for (String str : listStr) {
            String[] bufWords = str.split(" ");
            for (String word : bufWords) {
                if (word.matches(".*\\d.*")) {
                    //System.out.println("Совпадение! " + word + " " + i);
                    fileWriter.write(word + " ");
                }
            }
        }
        fileWriter.close();
    }

    /**
     * Метод считывает файл построчно и возвращаетв список строк
     * @param nameFile имя файла, который читать
     * @return список строк считанных из файла
     */
    public static ArrayList<String> readFile(String nameFile) {
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
