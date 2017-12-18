package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();
    public static ArrayList<String> list;

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
        String fileName = args[0];// "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1921\\test.txt";
        list = readFile(fileName);
        List<String> string;
        for (int i = 0; i < list.size(); i++) {
            string = Arrays.asList(list.get(i).split(" "));//all
            int size = string.size();
            //name
            String name = "";
            for (int j = 0; j < size - 3; j++) {
                name += string.get(j) + " ";
            }
            name = name.trim();
            //birthday
            int year = Integer.parseInt(string.get(size - 1)) - 1900;
            int month = Integer.parseInt(string.get(size - 2)) - 1;
            int day = Integer.parseInt(string.get(size - 3));
            PEOPLE.add(new Person(name, new Date(year, month, day)));
        }
        //System.out.println(PEOPLE.toString());
    }
}
