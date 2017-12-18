package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
Отслеживаем изменения
Считать с консоли 2 имени файла — file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines.
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME.
В оригинальном и редактируемом файлах пустых строк нет.

Пример:
оригинальный    редактированный    общий
file1:          file2:             результат:(lines)

строка1         строка1            SAME строка1
строка2                            REMOVED строка2
строка3         строка3            SAME строка3
строка4                            REMOVED строка4
строка5         строка5            SAME строка5
                строка0            ADDED строка0
строка1         строка1            SAME строка1
строка2                            REMOVED строка2
строка3         строка3            SAME строка3
                строка5            ADDED строка5
строка4         строка4            SAME строка4
строка5                            REMOVED строка5


Требования:
1. Класс Solution должен содержать класс LineItem.
2. Класс Solution должен содержать enum Type.
3. Класс Solution должен содержать публичное статическое поле lines типа List, которое сразу проинициализировано.
4. В методе main(String[] args) программа должна считывать имена файлов с консоли (используй BufferedReader).
5. В методе main(String[] args) BufferedReader для считывания данных с консоли должен быть закрыт.
6. Программа должна считывать содержимое первого и второго файла (используй FileReader).
7. Потоки чтения из файлов (FileReader) должны быть закрыты.
8. Список lines должен содержать объединенную версию строк из файлов, где для каждой строки указана одна из операций ADDED, REMOVED, SAME.
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine(), file2 = reader.readLine();
        reader.close();

        /*String file1 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\1.txt";
        String file2 = "D:\\Google Диск\\Project\\Java\\JavaRushTasks\\2.JavaCore\\src\\com\\" +
                "javarush\\task\\task18\\task1808\\2.txt";*/

        ArrayList<String> list1 = readFile(file1);
        ArrayList<String> list2 = readFile(file2);

        int count1 = 0 , count2 = 0;
        while(true) {
            if (count1 < list1.size() && count2 < list2.size() && list1.get(count1).equals(list2.get(count2))) {
                lines.add(new LineItem(Type.SAME, list2.get(count2)));
                count1++;
                count2++;
            } else if (count2 < list2.size() && count1 + 1 < list1.size() && list2.get(count2).equals(list1.get(count1 + 1))) {
                lines.add(new LineItem(Type.REMOVED, list1.get(count1)));
                lines.add(new LineItem(Type.SAME, list1.get(count1 + 1)));
                count1++;
                count1++;
                count2++;
            } else if (count2 + 1 < list2.size() && count1 < list1.size()){
                lines.add(new LineItem(Type.ADDED, list2.get(count2)));
                lines.add(new LineItem(Type.SAME, list2.get(count2 + 1)));
                count1++;
                count2++;
                count2++;
            } else if (count1 < list1.size()) {
                lines.add(new LineItem(Type.REMOVED, list1.get(count1)));
                count1++;
            } else if (count2 < list2.size()){
                lines.add(new LineItem(Type.ADDED, list2.get(count2)));
                count2++;
            } else break;
        }

        /*for (LineItem x: lines) {
            System.out.println(x);
        }*/
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

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        @Override
        public String toString() {
           return (this.type + " " + this.line);
        }

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
