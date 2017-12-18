package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
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
        String fileName = args[0]; //"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1920\\test.txt";
        list = readFile(fileName);
        TreeMap< String, Double > map = new TreeMap<>();

        //add data in map
        for (String data : list){
            String surname = data.split(" ")[0];
            Double solary = Double.parseDouble(data.split(" ")[1]);
            if (!map.containsKey(surname)) map.put(surname, solary);
            else map.put(surname, map.get(surname) + solary);
        }

        //find maxSolary
        Double maxSolary = new Double(0);
        for (Map.Entry x : map.entrySet()){
            if (Double.compare(maxSolary, (Double)x.getValue()) < 0){
                maxSolary = (Double) x.getValue();
            }
        }

        //sout surname have maxSolary
        for (Map.Entry x : map.entrySet()){
            if (Double.compare(maxSolary, (Double)x.getValue()) == 0){
                System.out.println(x.getKey());
            }
        }
    }
}
