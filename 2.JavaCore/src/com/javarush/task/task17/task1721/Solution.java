package com.javarush.task.task17.task1721;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    /**Транзакционность.*/
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();

        //allLines = Files.readAllLines(Paths.get(fileName1));
        allLines.addAll(readFile(fileName1));
        forRemoveLines.addAll(readFile(fileName2));
        try {
            solution.joinData();
            System.out.println("Изменение прошло успешно успешно");
        } catch (CorruptedDataException exc) {
            System.out.println("Сбой! Неполное соответствие данных!");
        }

        //Check
        System.out.println(allLines);
        System.out.println(forRemoveLines);
        reader.close();
    }

    /**
     * Метод сопостовляет два списка.
     * Если список allLines содержит все строки из forRemoveLines,
     * то удаляет из списка allLines все строки, которые есть в forRemoveLines.
     * Иначе очищает allLines от данных и выбрасывает исключение CorruptedDataException
     */
    public void joinData() throws CorruptedDataException {
        int numberContainsLines = 0;

        //В этом методе можно форы заменить при помощи метода array.containsAll

        //проверяем количество совпадения
        for (int i = 0; i < forRemoveLines.size(); i++) {
            if (allLines.contains(forRemoveLines.get(i))) {
                numberContainsLines++;
            }
        }

        //чистим списки в зависимости от количества совпадений
        if (forRemoveLines.size() == numberContainsLines) {
            for (int i = 0; i < allLines.size(); ) {
                if (forRemoveLines.contains(allLines.get(i))) {
                    allLines.remove(i);
                } else {
                    i++;
                }
            }
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }

    /** Метод считывает файл построчно и возвращаетв список строк.
     * @param nameFile имя файла, который необходимо считать
     * @return список строк считанных из файла
     */
    public static ArrayList<String> readFile(String nameFile) {
        ArrayList<String> buffer = new ArrayList<>();
        String data;
        try {
            BufferedReader br = new BufferedReader(new FileReader(nameFile));
            while ((data = br.readLine()) != null) {
                buffer.add(data);
            }
            br.close();
        } catch (IOException exc) {
            System.out.println("Ошибка чтения из файла: " + exc.toString());
        }
        return buffer;
    }

}
