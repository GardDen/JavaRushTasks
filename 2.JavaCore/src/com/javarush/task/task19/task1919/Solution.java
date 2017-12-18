package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        String fileName = args[0];//"D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1919\\test.txt";
        list = readFile(fileName);
        //System.out.println(list.toString());

        TreeMap< String, Double > map = new TreeMap<>();

        for (String data : list){
            String surname = data.split(" ")[0];
            Double solary = Double.parseDouble(data.split(" ")[1]);
            if (!map.containsKey(surname)) map.put(surname, solary);
            else map.put(surname, map.get(surname) + solary);
        }

        for (Map.Entry x : map.entrySet()){
            System.out.printf("%s %s%n", x.getKey(), x.getValue().toString());
        }
    }




       /* ArrayList<Employee> ascii = new ArrayList<>();

        while (fileReader.ready()) {
            list.add(fileReader.read());
        }

        fileReader.close();

        while (true) {
            try {
                ascii.add(new Employee(list.get(0), analyz(list.get(0))));
            } catch (IndexOutOfBoundsException exc) {
                break;
            }
        }

        Collections.sort(ascii, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
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

    public static class Employee {
        private String surname;
        private Double salary;

        public Employee(String surname, Double salary) {
            this.surname = surname;
            this.salary = salary;
        }

        public String getNumberChar() {
            return surname;
        }

        public Double getCountChar() {
            return salary;
        }
    }*/
}
