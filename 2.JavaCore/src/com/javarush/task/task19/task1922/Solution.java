package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.StreamHandler;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
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

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filrName = reader.readLine();//"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1921\\test.txt";
        reader.close();
        ArrayList<String> list = readFile(filrName);
        for (String data : list) {
            int count = 0;
            String[] dataWords = data.split(" ");
            for (String word : dataWords) {
                for (String str : words) {
                    if (word.equals(str)) {
                        count++;
                    }
                }
            }
            if (count == 2) {
                System.out.println(data);
            }
        }
    }
}
